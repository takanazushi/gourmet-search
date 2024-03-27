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

    val (isIzakayaChacked, setIsIzakayaChaked) = remember {
        mutableStateOf(false)
    }

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

        GenreCheckbox(onGenreChackChange = onGenreChackChange)

        Divider()

        Spacer(modifier = Modifier.height(8.dp))

        LunchAndMidnightCheckBox(
            onLunchCheckedChange = onLunchCheckedChange,
            onMidnightCheckedChange = onMidnightCheckedChange
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

@Composable
fun GenreCheckbox(onGenreChackChange: (String) -> Unit) {

    val (isIZakayaChecked, setIzakayaChecked) = remember {
        mutableStateOf(false)
    }
    val (isDiningChecked, setDiningChecked) = remember {
        mutableStateOf(false)
    }
    val (isCreativeCuisineChecked, setCreativeCuisineChecked) = remember {
        mutableStateOf(false)
    }
    val (isJapaneseFoodChecked, setJapaneseFoodChecked) = remember {
        mutableStateOf(false)
    }
    val (isWesternFoodChecked, setWesternFoodChecked) = remember {
        mutableStateOf(false)
    }
    val (isItalianChecked, setItalianChecked) = remember {
        mutableStateOf(false)
    }
    val (isChineseFoodChecked, setChineseFoodChecked) = remember {
        mutableStateOf(false)
    }
    val (isGrilledMeatChecked, setGrilledMeatChecked) = remember {
        mutableStateOf(false)
    }
    val (isKoreanCuisineChecked, setKoreanCuisineChecked) = remember {
        mutableStateOf(false)
    }
    val (isAsianFoodChecked, setAsianFoodChecked) = remember {
        mutableStateOf(false)
    }
    val (isInternationalCuisineChecked, setInternationalCuisineChecked) = remember {
        mutableStateOf(false)
    }
    val (isKaraokeChecked, setKaraokeChecked) = remember {
        mutableStateOf(false)
    }
    val (isBarChecked, setBarChecked) = remember {
        mutableStateOf(false)
    }
    val (isRamenChecked, setRamenChecked) = remember {
        mutableStateOf(false)
    }
    val (isOkonomiyakiChecked, setOkonomiyakiChecked) = remember {
        mutableStateOf(false)
    }
    val (isCafeChecked, setCafeChecked) = remember {
        mutableStateOf(false)
    }
    val (isOthersChecked, setOthersChecked) = remember {
        mutableStateOf(false)
    }

    Column {

        Row {
            //チェックボックスを表示する
            CheckBoxAndTitleText(
                TitleName = "居酒屋",
                checked = isIZakayaChecked,
                onCheckedChange = {
                    setIzakayaChecked(!isIZakayaChecked)
                    onGenreChackChange(if (isIZakayaChecked == true) "G001" else "")
                }
            )
            Spacer(modifier = Modifier.width(30.dp))
            CheckBoxAndTitleText(
                TitleName = "ダイニングバー・バル",
                checked = isDiningChecked,
                onCheckedChange = {
                    setDiningChecked(!isDiningChecked)
                    onGenreChackChange(if (isDiningChecked == true) "G002" else "")
                }
            )
        }

        Row {
            CheckBoxAndTitleText(TitleName = "創作料理",
                checked = isCreativeCuisineChecked,
                onCheckedChange = {
                    setCreativeCuisineChecked(!isCreativeCuisineChecked)
                    onGenreChackChange(if (isCreativeCuisineChecked == true) "G003" else "")
                }
            )
            Spacer(modifier = Modifier.width(13.dp))
            CheckBoxAndTitleText(TitleName = "和食",
                checked = isJapaneseFoodChecked,
                onCheckedChange = {
                    setJapaneseFoodChecked(!isJapaneseFoodChecked)
                    onGenreChackChange(if (isJapaneseFoodChecked == true) "G004" else "")
                }
            )
            Spacer(modifier = Modifier.width(31.dp))
            CheckBoxAndTitleText(TitleName = "洋食",
                checked = isWesternFoodChecked,
                onCheckedChange = {
                    setWesternFoodChecked(!isWesternFoodChecked)
                    onGenreChackChange(if (isWesternFoodChecked == true) "G005" else "")
                }
            )
        }

        Row {
            CheckBoxAndTitleText(TitleName = "イタリアン・フレンチ",
                checked = isItalianChecked,
                onCheckedChange = {
                    setItalianChecked(!isItalianChecked)
                    onGenreChackChange(if (isItalianChecked == true) "G006" else "")
                }
            )
            Spacer(modifier = Modifier.width(28.dp))
            CheckBoxAndTitleText(TitleName = "中華",
                checked = isChineseFoodChecked,
                onCheckedChange = {
                    setChineseFoodChecked(!isChineseFoodChecked)
                    onGenreChackChange(if (isChineseFoodChecked == true) "G007" else "")
                }
            )
        }

        Row {
            CheckBoxAndTitleText(TitleName = "焼肉・ホルモン",
                checked = isGrilledMeatChecked,
                onCheckedChange = {
                    setGrilledMeatChecked(!isGrilledMeatChecked)
                    onGenreChackChange(if (isGrilledMeatChecked == true) "G008" else "")
                }
            )
            Spacer(modifier = Modifier.width(76.dp))
            CheckBoxAndTitleText(TitleName = "韓国料理",
                checked = isKaraokeChecked,
                onCheckedChange = {
                    setKaraokeChecked(!isKoreanCuisineChecked)
                    onGenreChackChange(if (isKoreanCuisineChecked == true) "G017" else "")
                }
            )
        }

        Row {
            CheckBoxAndTitleText(TitleName = "アジア・エスニック料理",
                checked = isAsianFoodChecked,
                onCheckedChange = {
                    setAsianFoodChecked(!isAsianFoodChecked)
                    onGenreChackChange(if (isAsianFoodChecked == true) "G009" else "")
                }
            )
            Spacer(modifier = Modifier.width(10.dp))
            CheckBoxAndTitleText(TitleName = "各国料理",
                checked = isInternationalCuisineChecked,
                onCheckedChange = {
                    setInternationalCuisineChecked(!isInternationalCuisineChecked)
                    onGenreChackChange(if (isInternationalCuisineChecked == true) "G010" else "")
                }
            )
        }

        Row {
            CheckBoxAndTitleText(TitleName = "カラオケ・パーティ",
                checked = isKaraokeChecked,
                onCheckedChange = {
                    setKaraokeChecked(!isKaraokeChecked)
                    onGenreChackChange(if (isKaraokeChecked == true) "G011" else "")
                }
            )
            Spacer(modifier = Modifier.width(44.dp))
            CheckBoxAndTitleText(TitleName = "バー\nカクテル",
                checked = isBarChecked,
                onCheckedChange = {
                    setBarChecked(!isBarChecked)
                    onGenreChackChange(if (isBarChecked == true) "G012" else "")
                }
            )
        }

        Row {
            CheckBoxAndTitleText(TitleName = "ラーメン",
                checked = isRamenChecked,
                onCheckedChange = {
                    setRamenChecked(!isRamenChecked)
                    onGenreChackChange(if (isRamenChecked == true) "G013" else "")
                }
            )
            Spacer(modifier = Modifier.width(30.dp))
            CheckBoxAndTitleText(TitleName = "お好み焼き・もんじゃ",
                checked = isOkonomiyakiChecked,
                onCheckedChange = {
                    setOkonomiyakiChecked(!isOkonomiyakiChecked)
                    onGenreChackChange(if (isOkonomiyakiChecked == true) "G016" else "")
                }
            )
        }

        Row {
            CheckBoxAndTitleText(TitleName = "カフェ・スイーツ",
                checked = isCafeChecked,
                onCheckedChange = {
                    setCafeChecked(!isCafeChecked)
                    onGenreChackChange(if (isCafeChecked == true) "G014" else "")
                }
            )
            Spacer(modifier = Modifier.width(30.dp))
            CheckBoxAndTitleText(TitleName = "その他グルメ",
                checked = isOthersChecked,
                onCheckedChange = {
                    setOthersChecked(!isOthersChecked)
                    onGenreChackChange(if (isOthersChecked == true) "G015" else "")
                }
            )
        }
    }
}

@Composable
fun LunchAndMidnightCheckBox(
    onLunchCheckedChange: (Boolean) -> Unit,
    onMidnightCheckedChange: (Boolean) -> Unit
) {
    val (isLaunchChecked, setLaunchChecked) = remember {
        mutableStateOf(false)
    }
    val (isMidnightChecked, setMidnightChecked) = remember {
        mutableStateOf(false)
    }


    Row {
        //ランチ有無チェックボックス
        CheckBoxAndTitleText(
            TitleName = "ランチあり",
            checked = isLaunchChecked,
            onCheckedChange = {
                setLaunchChecked(!isLaunchChecked)
                onLunchCheckedChange(isLaunchChecked)
            }
        )
        Spacer(modifier = Modifier.width(60.dp))

        //23時以降も営業してるところがいいかどうかのチェックボックス
        CheckBoxAndTitleText(
            TitleName = "23時以降も営業",
            checked = isMidnightChecked,
            onCheckedChange = {
                setMidnightChecked(!isMidnightChecked)
                onMidnightCheckedChange(isMidnightChecked)
            }
        )
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