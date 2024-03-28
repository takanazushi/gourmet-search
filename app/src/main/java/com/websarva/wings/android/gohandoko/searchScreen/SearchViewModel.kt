package com.websarva.wings.android.gohandoko.searchScreen

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SearchViewModel:ViewModel() {
    val lunch= mutableStateOf(false)
    var midnight= mutableStateOf(false)
    var selectedDistance = mutableStateOf(Triple("300m", "歩いて約5分圏内のお店を検索します", 1))
    var expanded = mutableStateOf(false)
    var keyWordList= mutableStateOf("")


    val isIzakayaChecked= mutableStateOf(false)
    val isDiningChecked= mutableStateOf(false)
    val isCreativeCuisineChecked= mutableStateOf(false)
    val isJapaneseFoodChecked= mutableStateOf(false)
    val isWesternFoodChecked= mutableStateOf(false)
    val isItalianChecked= mutableStateOf(false)
    val isChineseFoodChecked= mutableStateOf(false)
    val isGrilledMeatChecked= mutableStateOf(false)
    val isKoreanCuisineChecked= mutableStateOf(false)
    val isAsianFoodChecked= mutableStateOf(false)
    val isInternationalCuisineChecked= mutableStateOf(false)
    val isKaraokeChecked= mutableStateOf(false)
    val isBarChecked= mutableStateOf(false)
    val isRamenChecked= mutableStateOf(false)
    val isOkonomiyakiChecked= mutableStateOf(false)
    val isCafeChecked= mutableStateOf(false)
    val isOthersChecked= mutableStateOf(false)

}