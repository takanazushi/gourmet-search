package com.websarva.wings.android.gohandoko.searchScreen

import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    onGenreChackChange: (String) -> Unit,

    //距離が変更されたときのコールバック関数
    onRangeChange: (Int) -> Unit,

    //キーワードが変更されたときのコールバック関数
    onKeyWordChange: (String) -> Unit
) {

    Column(
        modifier = Modifier
            //Columnの幅を最大に
            .fillMaxWidth()
            //余白を付ける
            .padding(16.dp)
            //垂直方法のスクロールを可能にする
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "検索画面",
            //横方向で中央に揃える
            modifier = Modifier.align(Alignment.CenterHorizontally),
            //文字のスタイルの変更
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )

        //縦方向にスペース
        Spacer(modifier = Modifier.height(8.dp))

        //線を入れる
        Divider()

        Spacer(modifier = Modifier.height(8.dp))

        //左揃えの文字
        Text(text = "キーワード入力", modifier = Modifier.align(Alignment.Start))

        Spacer(modifier = Modifier.height(8.dp))

        //これが無いと動かない
        //まだ実験的な機能らしい
        @OptIn(ExperimentalMaterial3Api::class) TextField(
            //初期値は空文字
            value = "",
            //キーワードが変更されたときのコールバック関数を代入
            onValueChange = onKeyWordChange,
            modifier = Modifier
                //Textfieldの幅を最大に
                .fillMaxWidth()
                //横方向に中央揃え
                .align(Alignment.CenterHorizontally),

            //仮で入ってるテキスト
            placeholder = { Text(text = "キーワードを入力してください") })


        Spacer(modifier = Modifier.height(16.dp))

        Divider()

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "現在地からの距離", modifier = Modifier.align(Alignment.Start))

        Spacer(modifier = Modifier.height(8.dp))

        //距離を選択するドロップダウンメニュー
        DistanceDropDownMenu(onRangeChange = onRangeChange)

        Spacer(modifier = Modifier.height(8.dp))

        Divider()

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "お店のジャンル", modifier = Modifier.align(Alignment.Start))

        Column {

            Row {
                //チェックボックスを表示する
                CheckBoxAndTitleText(TitleName = "居酒屋",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G001") })
                Spacer(modifier = Modifier.width(30.dp))
                CheckBoxAndTitleText(TitleName = "ダイニングバー・バル",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G002") })
            }

            Row {
                CheckBoxAndTitleText(TitleName = "創作料理",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G003") })
                Spacer(modifier = Modifier.width(13.dp))
                CheckBoxAndTitleText(TitleName = "和食",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G004") })
                Spacer(modifier = Modifier.width(31.dp))
                CheckBoxAndTitleText(TitleName = "洋食",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G005") })
            }

            Row {
                CheckBoxAndTitleText(TitleName = "イタリアン・フレンチ",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G006") })
                Spacer(modifier = Modifier.width(28.dp))
                CheckBoxAndTitleText(TitleName = "中華",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G007") })
            }

            Row {
                CheckBoxAndTitleText(TitleName = "焼肉・ホルモン",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G008") })
                Spacer(modifier = Modifier.width(76.dp))
                CheckBoxAndTitleText(TitleName = "韓国料理",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G017") })
            }

            Row {
                CheckBoxAndTitleText(TitleName = "アジア・エスニック料理",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G009") })
                Spacer(modifier = Modifier.width(10.dp))
                CheckBoxAndTitleText(TitleName = "各国料理",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G010") })
            }

            Row {
                CheckBoxAndTitleText(TitleName = "カラオケ・パーティ",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G011") })
                Spacer(modifier = Modifier.width(44.dp))
                CheckBoxAndTitleText(TitleName = "バー\nカクテル",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G012") })
            }

            Row {
                CheckBoxAndTitleText(TitleName = "ラーメン",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G013") })
                Spacer(modifier = Modifier.width(30.dp))
                CheckBoxAndTitleText(TitleName = "お好み焼き・もんじゃ",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G016") })
            }

            Row {
                CheckBoxAndTitleText(TitleName = "カフェ・スイーツ",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G014") })
                Spacer(modifier = Modifier.width(30.dp))
                CheckBoxAndTitleText(TitleName = "その他グルメ",
                    checked = false,
                    onCheckedChange = { onGenreChackChange("G015") })
            }
        }

        Divider()

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            //ランチ有無チェックボックス
            CheckBoxAndTitleText(
                TitleName = "ランチあり", checked = false, onCheckedChange = onLunchCheckedChange
            )
            Spacer(modifier = Modifier.width(60.dp))

            //23時以降も営業してるところがいいかどうかのチェックボックス
            CheckBoxAndTitleText(
                TitleName = "23時以降も営業",
                checked = false,
                onCheckedChange = onMidnightCheckedChange
            )
        }

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

@Composable
fun DistanceDropDownMenu(
    //距離が選択されたときに呼び出される関数
    onRangeChange: (Int) -> Unit
) {

    //距離のリスト
    //Firstがドロップダウンに表示する距離
    //Secondがかかる時間
    //ThirdがAPIに渡すときの値
    val distances = listOf(
        Triple("300m", "歩いて約5分圏内のお店を検索します", 1),
        Triple("500m", "歩いて約10分圏内のお店を検索します", 2),
        Triple("1km", "歩いて約15分程度のお店を検索します", 3),
        Triple("2km", "歩いて約30分程度のお店を検索します", 4),
        Triple("3km", "歩いて約45分程度のお店を検索します", 5),
    )

    //選択された距離を保持する状態を定義する
    var selectedDistance by remember { mutableStateOf(distances.first()) }

    //ドロップダウンの状態を保持する
    var expanded by remember {
        mutableStateOf(false)
    }

    //ドロップダウンメニューのアイコンを設定
    val icon = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown

    //これが無いとエラー
    @OptIn(ExperimentalMaterialApi::class)
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {

        //テキストフィールドを利用して、ドロップダウンメニューの開閉を制御する
        @OptIn(ExperimentalMaterial3Api::class)
        TextField(
            //書き込み不可
            readOnly = true,
            //選択された距離を表示する
            value = selectedDistance.first,
            //編集不可なので空
            onValueChange = {},
            //ドロップダウンメニューのアイコン
            trailingIcon = { Icon(imageVector = icon, contentDescription = null) },
            modifier = Modifier
                //幅を最大に
                .fillMaxWidth()

                //クリックでドロップダウンメニュー開閉をする
                .clickable { expanded = !expanded }
        )

        //ドロップダウンメニューの中にあるアイテム
        ExposedDropdownMenu(
            expanded = expanded,
            //ドロップダウンメニューを閉じる
            onDismissRequest = { expanded = false }) {

            //リストの各要素に対して処理を行う
            distances.forEach { distance ->

                //ドロップダウンメニューがクリックされたとき
                DropdownMenuItem(onClick = {
                    //選択された距離に更新
                    selectedDistance = distance

                    //ドロップダウンメニューを閉じる
                    expanded = false

                    //APIに渡す値を設定
                    onRangeChange(distance.third)
                }) {
                    //ドロップダウンメニューの表示
                    Text(text = distance.first)
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(10.dp))

    //説明文を表示
    Text(text = selectedDistance.second)
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
    GoHandokoTheme {
        SearchScreen(
            onSearchClick = ::dummySearchClick,
            onLunchCheckedChange = ::dummyCheckedChange,
            onMidnightCheckedChange = ::dummyCheckedChange,
            onGenreChackChange = ::dummyGenreChange,
            onRangeChange = ::dummyRangeChange,
            onKeyWordChange = ::dummyKeywordChange
        )
    }
}