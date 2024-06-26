package com.websarva.wings.android.gohandoko.searchScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.PreviewActivity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GenreCheckbox(searchData: SearchData, viewModel: SearchViewModel) {
    val isIZakayaChecked by viewModel.isIzakayaChecked
    val isDiningChecked by viewModel.isDiningChecked
    val isCreativeCuisineChecked by viewModel.isCreativeCuisineChecked
    val isJapaneseFoodChecked by viewModel.isJapaneseFoodChecked
    val isWesternFoodChecked by viewModel.isWesternFoodChecked
    val isItalianChecked by viewModel.isItalianChecked
    val isChineseFoodChecked by viewModel.isChineseFoodChecked
    val isGrilledMeatChecked by viewModel.isGrilledMeatChecked
    val isKoreanCuisineChecked by viewModel.isKoreanCuisineChecked
    val isAsianFoodChecked by viewModel.isAsianFoodChecked
    val isInternationalCuisineChecked by viewModel.isInternationalCuisineChecked
    val isKaraokeChecked by viewModel.isKaraokeChecked
    val isBarChecked by viewModel.isBarChecked
    val isRamenChecked by viewModel.isRamenChecked
    val isOkonomiyakiChecked by viewModel.isOkonomiyakiChecked
    val isCafeChecked by viewModel.isCafeChecked
    val isOthersChecked by viewModel.isOthersChecked

    Column {

        Log.d(
            "haihai",
            "viewModel.isIzakayaChecked.value:${viewModel.isIzakayaChecked.value}"
        )

        Column{
            Text(text = "日本食", modifier = Modifier.align(Alignment.Start), fontSize = 20.sp)

            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.width(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {

            //チェックボックスを表示する
            CheckBoxAndTitleText(TitleName = "和食",
                checked = isJapaneseFoodChecked,
                onCheckedChange = {
                    viewModel.isJapaneseFoodChecked.value = !isJapaneseFoodChecked
                    searchData.genreWords=searchData.genreWords.apply {
                        if(viewModel.isJapaneseFoodChecked.value){
                            add("G004")
                        }
                        else{
                            remove("G004")
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.width(60.dp))

            CheckBoxAndTitleText(TitleName = "お好み焼き・もんじゃ",
                checked = isOkonomiyakiChecked,
                onCheckedChange = {
                    viewModel.isOkonomiyakiChecked.value = !isOkonomiyakiChecked
                    searchData.genreWords=searchData.genreWords.apply {
                        if(viewModel.isOkonomiyakiChecked.value){
                            add("G016")
                        }
                        else{
                            remove("G016")
                        }
                    }
                }
            )

        }


        Row(modifier = Modifier.fillMaxWidth()) {

            CheckBoxAndTitleText(
                TitleName = "居酒屋",
                checked = isIZakayaChecked,
                onCheckedChange = {
                    viewModel.isIzakayaChecked.value = !isIZakayaChecked
                    searchData.genreWords=searchData.genreWords.apply {
                        if(viewModel.isIzakayaChecked.value){
                            add("G001")
                        }
                        else{
                            remove("G001")
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.width(43.dp))
            CheckBoxAndTitleText(TitleName = "ラーメン",
                checked = isRamenChecked,
                onCheckedChange = {
                    viewModel.isRamenChecked.value = !isRamenChecked
                    searchData.genreWords=searchData.genreWords.apply {
                        if(viewModel.isRamenChecked.value){
                            add("G013")
                        }
                        else{
                            remove("G013")
                        }
                    }
                }
            )

        }

        Spacer(modifier = Modifier.height(20.dp))



        Column{
            Text(text = "海外料理", modifier = Modifier.align(Alignment.Start), fontSize = 20.sp)

            Spacer(modifier = Modifier.height(10.dp))
        }

        Row(modifier = Modifier.fillMaxWidth()){
            CheckBoxAndTitleText(TitleName = "洋食",
                checked = isWesternFoodChecked,
                onCheckedChange = {
                    viewModel.isWesternFoodChecked.value = !isWesternFoodChecked
                    searchData.genreWords=searchData.genreWords.apply {
                        if(viewModel.isWesternFoodChecked.value){
                            add("G005")
                        }
                        else{
                            remove("G005")
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.width(60.dp))

            CheckBoxAndTitleText(TitleName = "イタリアン・フレンチ",
                checked = isItalianChecked,
                onCheckedChange = {
                    viewModel.isItalianChecked.value = !isItalianChecked
                    searchData.genreWords=searchData.genreWords.apply {
                        if(viewModel.isItalianChecked.value){
                            add("G006")
                        }
                        else{
                            remove("G006")
                        }
                    }
                }
            )
        }

        Row(modifier = Modifier.fillMaxWidth()){

            CheckBoxAndTitleText(TitleName = "中華",
                checked = isChineseFoodChecked,
                onCheckedChange = {
                    viewModel.isChineseFoodChecked.value = !isChineseFoodChecked
                    searchData.genreWords=searchData.genreWords.apply {
                        if(viewModel.isChineseFoodChecked.value){
                            add("G007")
                        }
                        else{
                            remove("G007")
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.width(60.dp))

            CheckBoxAndTitleText(TitleName = "焼肉・ホルモン",
                checked = isGrilledMeatChecked,
                onCheckedChange = {
                    viewModel.isGrilledMeatChecked.value = !isGrilledMeatChecked
                    searchData.genreWords=searchData.genreWords.apply {
                        if(viewModel.isGrilledMeatChecked.value){
                            add("G008")
                        }
                        else{
                            remove("G008")
                        }
                    }
                }
            )


        }

        Row (modifier = Modifier.fillMaxWidth()){
            CheckBoxAndTitleText(TitleName = "韓国料理",
                checked = isKoreanCuisineChecked,
                onCheckedChange = {
                    viewModel.isKoreanCuisineChecked.value = !isKoreanCuisineChecked
                    searchData.genreWords=searchData.genreWords.apply {
                        if(viewModel.isKoreanCuisineChecked.value){
                            add("G017")
                        }
                        else{
                            remove("G017")
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.width(27.dp))

            CheckBoxAndTitleText(TitleName = "各国料理",
                checked = isInternationalCuisineChecked,
                onCheckedChange = {
                    viewModel.isInternationalCuisineChecked.value = !isInternationalCuisineChecked
                    searchData.genreWords=searchData.genreWords.apply {
                        if(viewModel.isInternationalCuisineChecked.value){
                            add("G010")
                        }
                        else{
                            remove("G010")
                        }
                    }
                }
            )


        }

        CheckBoxAndTitleText(TitleName = "アジア・エスニック料理",
            checked = isAsianFoodChecked,
            onCheckedChange = {
                viewModel.isAsianFoodChecked.value = !isAsianFoodChecked
                searchData.genreWords=searchData.genreWords.apply {
                    if(viewModel.isAsianFoodChecked.value){
                        add("G009")
                    }
                    else{
                        remove("G009")
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))




        Column{
            Text(text = "飲食＆娯楽", modifier = Modifier.align(Alignment.Start), fontSize = 20.sp)

            Spacer(modifier = Modifier.height(10.dp))
        }

        CheckBoxAndTitleText(TitleName = "カラオケ・パーティ",
            checked = isKaraokeChecked,
            onCheckedChange = {
                viewModel.isKaraokeChecked.value = !isKaraokeChecked
                searchData.genreWords=searchData.genreWords.apply {
                    if(viewModel.isKaraokeChecked.value){
                        add("G011")
                    }
                    else{
                        remove("G011")
                    }
                }
            }
        )

        CheckBoxAndTitleText(
            TitleName = "ダイニングバー・バル",
            checked = isDiningChecked,
            onCheckedChange = {
                viewModel.isDiningChecked.value = !isDiningChecked
                searchData.genreWords=searchData.genreWords.apply {
                    if(viewModel.isDiningChecked.value){
                        add("G002")
                    }
                    else{
                        remove("G002")
                    }
                }
            }
        )

        CheckBoxAndTitleText(TitleName = "バー・カクテル",
            checked = isBarChecked,
            onCheckedChange = {
                viewModel.isBarChecked.value = !isBarChecked
                searchData.genreWords=searchData.genreWords.apply {
                    if(viewModel.isBarChecked.value){
                        add("G012")
                    }
                    else{
                        remove("G012")
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))


        Column{
            Text(text = "その他", modifier = Modifier.align(Alignment.Start), fontSize = 20.sp)

            Spacer(modifier = Modifier.height(10.dp))
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            CheckBoxAndTitleText(TitleName = "創作料理",
                checked = isCreativeCuisineChecked,
                onCheckedChange = {
                    viewModel.isCreativeCuisineChecked.value = !isCreativeCuisineChecked
                    searchData.genreWords=searchData.genreWords.apply {
                        if(viewModel.isCreativeCuisineChecked.value){
                            add("G003")
                        }
                        else{
                            remove("G003")
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.width(28.dp))

            CheckBoxAndTitleText(TitleName = "カフェ・スイーツ",
                checked = isCafeChecked,
                onCheckedChange = {
                    viewModel.isCafeChecked.value = !isCafeChecked
                    searchData.genreWords=searchData.genreWords.apply {
                        if(viewModel.isCafeChecked.value){
                            add("G014")
                        }
                        else{
                            remove("G014")
                        }
                    }
                }
            )
    

        }

        CheckBoxAndTitleText(TitleName = "その他グルメ",
            checked = isOthersChecked,
            onCheckedChange = {
                viewModel.isOthersChecked.value = !isOthersChecked
                searchData.genreWords=searchData.genreWords.apply {
                    if(viewModel.isOthersChecked.value){
                        add("G015")
                    }
                    else{
                        remove("G015")
                    }
                }
            }
        )
    }


}

@Preview
@Composable
fun PreviewActivity(){
    val dummySearchData=SearchData()
    val dummyViewMode=SearchViewModel()

    GenreCheckbox(searchData = dummySearchData, viewModel =dummyViewMode )
}