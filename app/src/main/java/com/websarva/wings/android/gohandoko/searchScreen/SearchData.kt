package com.websarva.wings.android.gohandoko.searchScreen

data class SearchData(
    var lunchService:Boolean=false,
    var openAfterMidnight:Boolean=false,
    var range:Int=1,
    var genreWords:ArrayList<String> =ArrayList(),
    var keyWord:String=""
){
    var genreWord:ArrayList<String>
        get()=genreWords
        set(value){
            genreWords.clear()
            genreWords.addAll(value.distinct())
        }
}
