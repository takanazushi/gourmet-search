package com.websarva.wings.android.gohandoko.searchScreen

import android.widget.CheckBox
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
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.websarva.wings.android.gohandoko.ui.theme.GoHandokoTheme

@Composable
fun SearchScreen(
    onSearchClick: () -> Unit,
    onLunchCheckedChange: (Boolean) -> Unit,
    onMidnightCheckedChange: (Boolean) -> Unit,
    onGenreChackChange: (String) -> Unit,
    onRangeChange: (Int) -> Unit,
    onKeyWordChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "検索画面",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Divider()

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "キーワード入力", modifier = Modifier.align(Alignment.Start))

        Spacer(modifier = Modifier.height(8.dp))

        @OptIn(ExperimentalMaterial3Api::class)
        TextField(
            value = "",
            onValueChange = onKeyWordChange,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            placeholder = { Text(text = "キーワードを入力してください")}
        )

        Spacer(modifier = Modifier.height(16.dp))

        Divider()

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "現在地からの距離", modifier = Modifier.align(Alignment.Start))

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {

            RadioButton(selected = false, onClick = { onRangeChange(1)}, modifier = Modifier.padding(end=8.dp))
            Text(text = "300m")
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "歩いて5分程度")

        }

        Row(verticalAlignment = Alignment.CenterVertically) {

            RadioButton(selected = false, onClick = { onRangeChange(1)}, modifier = Modifier.padding(end=8.dp))
            Text(text = "500m")
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "歩いて10分程度")

        }

        Row(verticalAlignment = Alignment.CenterVertically) {

            RadioButton(selected = false, onClick = { onRangeChange(1)}, modifier = Modifier.padding(end=8.dp))
            Text(text = "1km")
            Spacer(modifier = Modifier.width(30.dp))
            Text(text = "歩いて15分程度")

        }

        Row(verticalAlignment = Alignment.CenterVertically) {

            RadioButton(selected = false, onClick = { onRangeChange(1)}, modifier = Modifier.padding(end=8.dp))
            Text(text = "2km")
            Spacer(modifier = Modifier.width(30.dp))
            Text(text = "歩いて30分程度")

        }

        Row(verticalAlignment = Alignment.CenterVertically) {

            RadioButton(selected = false, onClick = { onRangeChange(1)}, modifier = Modifier.padding(end=8.dp))
            Text(text = "3km")
            Spacer(modifier = Modifier.width(30.dp))
            Text(text = "歩いて45分程度")

        }

        Spacer(modifier = Modifier.height(8.dp))

        Divider()

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "お店のジャンル", modifier = Modifier.align(Alignment.Start))

        Column {

            Row{
                CheckBoxAndTitleText(TitleName = "居酒屋", checked = false, onCheckedChange = {onGenreChackChange("G001")})
                Spacer(modifier = Modifier.width(30.dp))
                CheckBoxAndTitleText(TitleName = "ダイニングバー・バル", checked = false, onCheckedChange = {onGenreChackChange("G002")})
            }

            Row{
                CheckBoxAndTitleText(TitleName = "創作料理", checked = false, onCheckedChange = {onGenreChackChange("G003")})
                Spacer(modifier = Modifier.width(13.dp))
                CheckBoxAndTitleText(TitleName = "和食", checked = false, onCheckedChange = {onGenreChackChange("G004")})
                Spacer(modifier = Modifier.width(31.dp))
                CheckBoxAndTitleText(TitleName = "洋食", checked = false, onCheckedChange = {onGenreChackChange("G005")})
            }

            Row{
                CheckBoxAndTitleText(TitleName = "イタリアン・フレンチ", checked = false, onCheckedChange = {onGenreChackChange("G006")})
                Spacer(modifier = Modifier.width(28.dp))
                CheckBoxAndTitleText(TitleName = "中華", checked = false, onCheckedChange = {onGenreChackChange("G007")})
            }

            Row{
                CheckBoxAndTitleText(TitleName = "焼肉・ホルモン", checked = false, onCheckedChange = {onGenreChackChange("G008")})
                Spacer(modifier = Modifier.width(76.dp))
                CheckBoxAndTitleText(TitleName = "韓国料理", checked = false, onCheckedChange = {onGenreChackChange("G017")})
            }

            Row{
                CheckBoxAndTitleText(TitleName = "アジア・エスニック料理", checked = false, onCheckedChange = {onGenreChackChange("G009")})
                Spacer(modifier = Modifier.width(10.dp))
                CheckBoxAndTitleText(TitleName = "各国料理", checked = false, onCheckedChange = {onGenreChackChange("G010")})
            }

            Row{
                CheckBoxAndTitleText(TitleName = "カラオケ・パーティ", checked = false, onCheckedChange = {onGenreChackChange("G011")})
                Spacer(modifier = Modifier.width(44.dp))
                CheckBoxAndTitleText(TitleName = "バー\nカクテル", checked = false, onCheckedChange = {onGenreChackChange("G012")})
            }

            Row{
                CheckBoxAndTitleText(TitleName = "ラーメン", checked = false, onCheckedChange = {onGenreChackChange("G013")})
                Spacer(modifier = Modifier.width(30.dp))
                CheckBoxAndTitleText(TitleName = "お好み焼き・もんじゃ", checked = false, onCheckedChange = {onGenreChackChange("G016")})
            }

            Row{
                CheckBoxAndTitleText(TitleName = "カフェ・スイーツ", checked = false, onCheckedChange = {onGenreChackChange("G014")})
                Spacer(modifier = Modifier.width(30.dp))
                CheckBoxAndTitleText(TitleName = "その他グルメ", checked = false, onCheckedChange = {onGenreChackChange("G015")})
            }
        }

        Divider()

        Spacer(modifier = Modifier.height(8.dp))

        Row{
            CheckBoxAndTitleText(TitleName = "ランチあり", checked = false, onCheckedChange = onLunchCheckedChange)
            Spacer(modifier = Modifier.width(60.dp))
            CheckBoxAndTitleText(TitleName = "23時以降も営業", checked = false, onCheckedChange = onMidnightCheckedChange)
        }

        Divider()

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onSearchClick,
            modifier = Modifier.align(Alignment.CenterHorizontally) ) {
            Text(text = "検索")
        }


    }
}

@Composable
fun CheckBoxAndTitleText(
    TitleName:String,
    checked: Boolean,
    onCheckedChange:(Boolean)->Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
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