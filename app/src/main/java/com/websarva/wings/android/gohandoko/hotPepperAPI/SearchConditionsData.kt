package com.websarva.wings.android.gohandoko.hotPepperAPI

/**ホットペッパーグルメの検索条件を保持するデータクラス
 * @param lat 緯度
 * @param lng 経度
 * @param lunch ランチ営業有無
 * @param midnight ２３時以降も営業
 * @param range 検索範囲距離
 * @param keyWordList キーワードのリスト
 * @param genreCdList ジャンルのリスト
 * */
data class SearchConditionsData(
    var lat: Double?,
    var lng: Double?,
    var lunch: Int,
    var midnight: Int,
    var range: Int,
    var keyWordList: String,
    var genreCdList: ArrayList<String>
)