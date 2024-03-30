package com.websarva.wings.android.gohandoko.detailsScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.websarva.wings.android.gohandoko.hotPepperAPI.SearchResultsData
import com.websarva.wings.android.gohandoko.ui.theme.GoHandokoTheme

@Composable
fun DetealsScreen(navController: NavController, data: SearchResultsData) {

    GoHandokoTheme {
        Column {
            Button(onClick = { navController.popBackStack() }) {

                Text(text = "戻る")
            }

            Column {
                Image(
                    painter = rememberImagePainter(data = data.photo),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .height(300.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(text = "店名：", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "${data.name}")
                }

                Row {
                    Text(text = "住所：", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "${data.address}")
                }

                Row {
                    Text(text = "アクセス：", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "${data.access}")
                }

                Row {
                    Text(text = "営業時間：", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "${data.open}")
                }

                Row {
                    Text(text = "定休日：", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "${data.close}")
                }

                Row {
                    Text(text = "ランチ：", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "${data.lunch}")
                }

                Row {
                    Text(text = "23時以降の営業：", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "${data.midnight}")
                }


            }
        }


    }

    Log.d("Deteal", "launchサービス：${data.access}")
}

@Preview
@Composable
fun Preview() {
    val dummyData = SearchResultsData(
        id = "1",
        name = "テストのお店",
        address = "テスト県テスト市テストテストテスト111-1",
        lat = 134.55555,
        lng = 34.555555,
        lunch = "ランチなし",
        midnight = "営業無し",
        url = "hogehogehogehoge",
        access = "テスト駅から徒歩100分",
        thumbnail = "dami-dami-dami-dami",
        photo = "dami-dami-dami-dami-",
        genre = "居酒屋",
        open = "月・水・金",
        close = "火・木"
    )

    val navController = rememberNavController()

    DetealsScreen(navController = navController, data = dummyData)
}