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
import com.websarva.wings.android.gohandoko.ui.theme.GoHandokoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity(), ShopInfoAsyncTask.ConfirmAsyncListener, LocationListener {

    private val isProgressShowing = mutableStateOf(false)

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
                }
            }
        }

        mGenereCdList.add("G001")


    }

    override fun onLocationChanged(location: Location) {
        CoroutineScope(Dispatchers.Main).launch {
            val searchConditionsData = SearchConditionsData(
                lat = locationInfomation.latitude ?: 0.0,
                lng = locationInfomation.longitude ?: 0.0,
                lunch = 0,
                range = 1,
                genreCdList = mGenereCdList,
                midnightMeal = 0,
                keyWordList = mKeyWordList
            )

            CatchShopInfo(this@MainActivity, this@MainActivity).callHotPepperAPI(
                searchConditionsData
            )
        }
    }

    override fun shopInfoAsyncCallBack(searchResultsDataArray: ArrayList<SearchResultsData>) {
        for (gourmet in searchResultsDataArray) {
            Log.d(
                "api",
                "名前: ${gourmet.name}, 住所: ${gourmet.address},画像URL:${gourmet.thumbnail}"
            )
        }

        Log.d("api","緯度：${locationInfomation.latitude},経度:${locationInfomation.longitude}")
    }

    override fun showProgress() {
        isProgressShowing.value = true
    }

    override fun hideProgress() {
        isProgressShowing.value = false
    }
}