package com.websarva.wings.android.gohandoko.searchScreen

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewmodel.CreationExtras
import com.websarva.wings.android.gohandoko.getLocationInfo.LocationInfomation
import com.websarva.wings.android.gohandoko.hotPepperAPI.CatchShopInfo
import com.websarva.wings.android.gohandoko.hotPepperAPI.SearchConditionsData
import com.websarva.wings.android.gohandoko.hotPepperAPI.SearchResultsData
import com.websarva.wings.android.gohandoko.hotPepperAPI.ShopInfoAsyncTask
import com.websarva.wings.android.gohandoko.ui.theme.GoHandokoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchActivity : ComponentActivity(), ShopInfoAsyncTask.ConfirmAsyncListener,
    LocationListener {

    private val isProgressShowing = mutableStateOf(false)
    private val serchData = SearchData()

    val mGenereCdList = arrayListOf<String>()
    val mKeyWordList = arrayListOf<String>()

    private lateinit var locationInfomation: LocationInfomation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationInfomation = LocationInfomation(this, this)
        locationInfomation.getLocation()

        setContent {
            GoHandokoTheme {
                if (isProgressShowing.value) {
                    CircularProgressIndicator()
                } else {
                    SearchScreen(
                        onSearchClick = ::search,
                        onLunchCheckedChange = { serchData.lunchService = it },
                        onMidnightCheckedChange = { serchData.openAfterMidnight = it },
                        onGenreChackChange = { code ->
                            serchData.genreWords = ArrayList(serchData.genreWords.apply {
                                if (contains(code)) {
                                    remove(code)
                                } else {
                                    add(code)
                                }
                            })
                        },
                        onRangeChange = { serchData.range = it },
                        onKeyWordChange = { serchData.keyWord = it.replace("　"," ") }
                    )
                }
            }
        }

        //mGenereCdList.add("G001")


    }

    private fun search() {
        CoroutineScope(Dispatchers.Main).launch {
            val searchConditionsData = SearchConditionsData(
                lat = locationInfomation.latitude ?: 0.0,
                lng = locationInfomation.longitude ?: 0.0,
                lunch = if (serchData.lunchService) 1 else 0,
                range = serchData.range,
                genreCdList = serchData.genreWords,
                midnight = if(serchData.openAfterMidnight)1 else 0,
                keyWordList = if(serchData.keyWord.isNotEmpty())  serchData.keyWord else ""
            )

            CatchShopInfo(this@SearchActivity, this@SearchActivity).callHotPepperAPI(
                searchConditionsData
            )
        }
    }

    override fun onLocationChanged(location: Location) {

    }

    override fun shopInfoAsyncCallBack(searchResultsDataArray: ArrayList<SearchResultsData>) {
        for (gourmet in searchResultsDataArray) {
            Log.d(
                "api",
                "名前: ${gourmet.name}, 住所: ${gourmet.address},URL:${gourmet.url}"
            )
        }

        Log.d("api", "緯度：${locationInfomation.latitude},経度:${locationInfomation.longitude}")
    }

    override fun showProgress() {
        isProgressShowing.value = true
    }

    override fun hideProgress() {
        isProgressShowing.value = false
    }
}


