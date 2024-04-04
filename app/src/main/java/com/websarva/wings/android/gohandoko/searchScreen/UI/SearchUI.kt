package com.websarva.wings.android.gohandoko.searchScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.websarva.wings.android.gohandoko.ui.theme.GoHandokoTheme


/**検索画面のUI関数**/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    //検索ボタンがクリックされたときのコールバック関数
    onSearchClick: () -> Unit,

    //ランチ有無チェックボックスの状態が変更されたときのコールバック関数
    onLunchCheckedChange: (Boolean) -> Unit,

    //23時以降の営業があるかどうかチェックボックスの状態が変更されたときのコールバック関数
    onMidnightCheckedChange: (Boolean) -> Unit,

    //ジャンルのチェックボックスの状態が変更されたときのコールバック関数
    //onGenreChackChange: (String) -> Unit,
    searchData: SearchData,

    //距離が変更されたときのコールバック関数
    onRangeChange: (Int) -> Unit,

    //キーワードが変更されたときのコールバック関数
    onKeyWordChange: (String) -> Unit
) {
    val viewModel: SearchViewModel = viewModel()

    var keyword = viewModel.keyWordList.value

    Column(
        modifier = Modifier
            //Columnの幅を最大に
            .fillMaxWidth()
            //余白を付ける
            .padding(16.dp)
            //垂直方法のスクロールを可能にする
            .verticalScroll(rememberScrollState())
    ) {

        //あったらスクロールしないと検索ボタンが出てこない
        //検索ボタンあれば検索画面とわかるのかどうか……
        Text(
            text = "検索画面",
            //横方向で中央に揃える
            modifier = Modifier.align(Alignment.CenterHorizontally),
            //文字のスタイルの変更
            style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold)
        )

        //縦方向にスペース
        Spacer(modifier = Modifier.height(10.dp))

        //線を入れる
        Divider()

        Spacer(modifier = Modifier.height(10.dp))

        //左揃えの文字
        Text(text = "キーワード入力", modifier = Modifier.align(Alignment.Start), fontSize = 20.sp)

        Spacer(modifier = Modifier.height(10.dp))

        //これが無いと動かない
        //まだ実験的な機能らしい
        @OptIn(ExperimentalMaterial3Api::class) TextField(
            //初期値は空文字
            value = keyword,
            //キーワードが変更されたときのコールバック関数を代入
            onValueChange = { newKeyWord ->
                viewModel.keyWordList.value = newKeyWord
                onKeyWordChange(newKeyWord)
            },
            modifier = Modifier
                //Textfieldの幅を最大に
                .fillMaxWidth()
                //横方向に中央揃え
                .align(Alignment.CenterHorizontally),

            //仮で入ってるテキスト
            placeholder = { Text(text = "キーワードを入力してください") })


        Spacer(modifier = Modifier.height(16.dp))

        Divider()

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "現在地からの距離", modifier = Modifier.align(Alignment.Start), fontSize = 20.sp)

        Spacer(modifier = Modifier.height(10.dp))

        //距離を選択するドロップダウンメニュー
        DistanceDropDownMenu(onRangeChange = onRangeChange, viewModel = viewModel)

        Divider()

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "お店のジャンル", modifier = Modifier.align(Alignment.Start), fontSize = 20.sp)

        Spacer(modifier = Modifier.height(20.dp))

        GenreCheckbox(searchData = searchData, viewModel = viewModel)

        Divider()

        Spacer(modifier = Modifier.height(8.dp))

        LunchAndMidnightCheckBox(
            onLunchCheckedChange = onLunchCheckedChange,
            onMidnightCheckedChange = onMidnightCheckedChange,
            viewModel = viewModel
        )

        Divider()

        Spacer(modifier = Modifier.height(8.dp))

        //検索ボタン
        Button(
            onClick = onSearchClick, modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "検索")
        }


    }
}

@Composable
fun CheckBoxAndTitleText(
    //チェックボックスの横に表示するテキスト
    TitleName: String,
    //選択状態
    checked: Boolean,
    //選択状態が変更されたときに呼び出す関数
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        //縦方向で中央揃え
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            //チェックボックスの選択状態を設定
            checked = checked,
            //関数を呼び出す
            onCheckedChange = onCheckedChange
        )

        //テキストを表示する
        Text(text = TitleName)
    }
}


private fun dummySearchClick() {
    // 何も処理しない
}

private fun dummyCheckedChange(checked: Boolean) {
    // 何も処理しない
}

private fun dummyGenreChange(genre: String) {
    // 何も処理しない
}

private fun dummyRangeChange(range: Int) {
    // 何も処理しない
}

private fun dummyKeywordChange(keyword: String) {
    // 何も処理しない
}

@Preview
@Composable
fun Preview() {
    val previewSearchData = SearchData()

    GoHandokoTheme {
        SearchScreen(
            onSearchClick = ::dummySearchClick,
            onLunchCheckedChange = ::dummyCheckedChange,
            onMidnightCheckedChange = ::dummyCheckedChange,
            searchData = previewSearchData,
            onRangeChange = ::dummyRangeChange,
            onKeyWordChange = ::dummyKeywordChange
        )
    }
}