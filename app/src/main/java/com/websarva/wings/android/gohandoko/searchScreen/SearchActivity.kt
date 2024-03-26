package com.websarva.wings.android.gohandoko.searchScreen

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
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
                    //検索ボタン
                    Button(onClick = { search() }) {
                        Text(text = "検索")
                    }
                }
            }
        }

        mGenereCdList.add("G001")


    }

    private fun search() {
        CoroutineScope(Dispatchers.Main).launch {
            val searchConditionsData = SearchConditionsData(
                lat = locationInfomation.latitude ?: 0.0,
                lng = locationInfomation.longitude ?: 0.0,
                lunch = 0,
                range = 1,
                genreCdList = mGenereCdList,
                midnight = 0,
                keyWordList = mKeyWordList
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


