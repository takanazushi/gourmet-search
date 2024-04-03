package com.websarva.wings.android.gohandoko.hotPepperAPI

/**ホットペッパーグルメの検索結果を保持するデータクラス
 * @param id お店のID
 * @param name 店名
 * @param address 住所
 * @param lat 緯度
 * @param lng 経度
 * @param lunch ランチ営業有無
 * @param midnight 23時以降の営業有無
 * @param url URL
 * @param access アクセス
 * @param thumbnail サムネイル
 * @param photo 写真
 * @param open　営業時間
 * @param close　定休日
 * */
data class SearchResultsData(
    var id:String,
    var name:String,
    var address:String,
    var lat:Double,
    var lng:Double,
    var lunch:String,
    var midnight:String,
    var url:String,
    var access:String,
    var thumbnail:String,
    var photo:String,
    var genre:String,
    var open:String,
    var close:String,
    var budget:String
)