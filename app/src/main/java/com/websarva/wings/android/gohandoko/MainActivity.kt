package com.websarva.wings.android.gohandoko

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.mutableStateOf
import com.websarva.wings.android.gohandoko.getLocationInfo.LocationInfomation
import com.websarva.wings.android.gohandoko.hotPepperAPI.CatchShopInfo
import com.websarva.wings.android.gohandoko.hotPepperAPI.SearchConditionsData
import com.websarva.wings.android.gohandoko.hotPepperAPI.SearchResultsData
import com.websarva.wings.android.gohandoko.hotPepperAPI.ShopInfoAsyncTask
import com.websarva.wings.android.gohandoko.searchScreen.SearchData
import com.websarva.wings.android.gohandoko.searchScreen.SearchScreen
import com.websarva.wings.android.gohandoko.ui.theme.GoHandokoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity(), ShopInfoAsyncTask.ConfirmAsyncListener, LocationListener {

    private val isProgressShowing = mutableStateOf(false)
    private val serchData = SearchData()
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
                        searchData = serchData,
                        onRangeChange = { serchData.range = it },
                        onKeyWordChange = { serchData.keyWord = it.replace("　", " ") }
                    )
                }
            }
        }
    }

    private fun search() {
        Log.d("MainActivity", "検索はじまり")
        CoroutineScope(Dispatchers.Main).launch {
            val searchConditionsData = SearchConditionsData(
                lat = locationInfomation.latitude ?: 0.0,
                lng = locationInfomation.longitude ?: 0.0,
                lunch = if (serchData.lunchService) 1 else 0,
                range = serchData.range,
                genreCdList = serchData.genreWords,
                midnight = if (serchData.openAfterMidnight) 1 else 0,
                keyWordList = if (serchData.keyWord.isNotEmpty()) serchData.keyWord else ""
            )

            Log.d("MainActivity", "launchサービス：${serchData.lunchService}")
            Log.d("MainActivity", "深夜営業：${serchData.openAfterMidnight}")
            Log.d("MainActivity", "genre：${serchData.genreWords}")
            Log.d("MainActivity", "範囲：${serchData.range}")
            Log.d("MainActivity", "キーワード：" + serchData.keyWord)

            CatchShopInfo(this@MainActivity, this@MainActivity).callHotPepperAPI(
                searchConditionsData
            )
        }

        Log.d("MainActivity", "検索終わり")

    }

    override fun onLocationChanged(location: Location) {

    }

    override fun shopInfoAsyncCallBack(searchResultsDataArray: ArrayList<SearchResultsData>) {

        for (gourmet in searchResultsDataArray) {
            Log.d(
                "api",
                "名前: ${gourmet.name}, 住所: ${gourmet.address},URL:${gourmet.url},ジャンル${gourmet.genre}"
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