package com.websarva.wings.android.gohandoko.searchResultScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.websarva.wings.android.gohandoko.hotPepperAPI.SearchResultsData

@Composable
fun ResultActivity(
    navController: NavController,
    searchResultDataArray: ArrayList<SearchResultsData>,
    selectedData: MutableState<SearchResultsData?>
) {

    //受け取ったデータを２０個ずつに分割する
    val chukedData = searchResultDataArray.chunked(20)

    //最大のページ数
    val maxPage = chukedData.size

    //現在のページ数
    var nowPage = remember { mutableStateOf(0) }


    Column {

        Row {
            Button(onClick = { navController.popBackStack() }, modifier = Modifier.padding(16.dp)) {
                Text(text = "戻る")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Pager(currentPage = nowPage, maxPage = maxPage)
        }

        if(searchResultDataArray.isNotEmpty()){

            LazyColumn {
                this.items(chukedData[nowPage.value]) { data ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                selectedData.value = data
                                navController.navigate("detail_screen")
                            },
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        {
                            Image(
                                painter = rememberImagePainter(data = data.thumbnail),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(shape = RoundedCornerShape(4.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .align(Alignment.CenterVertically)
                            ) {
                                Text(text = data.name, fontWeight = FontWeight.Bold)
                                Text(text = data.access)
                                Text(text = data.genre)
                            }
                        }
                    }
                }


            }
        }else if(searchResultDataArray.isEmpty()){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Column{
                    Text(text = "お探しの条件では見つかりませんでした。")
                    Text(text = "検索条件を変更して再検索してください。")
                }

            }
        }


    }


}

@Composable
fun Pager(currentPage: MutableState<Int>, maxPage: Int) {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {

        IconButton(
            //ボタンを押したら１ページ戻る
            //coerceAtLeast(0) のおかげで、０未満に鳴らないようになっている
            onClick = { currentPage.value = (currentPage.value - 1).coerceAtLeast(0) },
            enabled = currentPage.value > 0
        )
        {
            Icon(Icons.Default.ArrowBack, contentDescription = "前のページ")
        }

        Text(
            "${currentPage.value + 1}/$maxPage",
            modifier = Modifier.padding(top = 10.dp),
            fontSize = 20.sp
        )

        IconButton(
            //ボタンを押したら１ページ進む
            //coerceAtMost(maxPage - 1)のおかげで、最大ページ数を越えないようになっている
            onClick = { currentPage.value = (currentPage.value + 1).coerceAtMost(maxPage - 1) },
            enabled = currentPage.value < maxPage - 1
        )
        {
            Icon(Icons.Default.ArrowForward, contentDescription = "次のページ")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewResultActivity() {
    val navController = rememberNavController()
    val searchResultDataArray = ArrayList<SearchResultsData>()
    val selectedData = mutableStateOf<SearchResultsData?>(null)

    ResultActivity(navController, searchResultDataArray, selectedData)
}




