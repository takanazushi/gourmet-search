package com.websarva.wings.android.gohandoko

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.mutableStateOf
import com.websarva.wings.android.gohandoko.hotPepperAPI.CatchShopInfo
import com.websarva.wings.android.gohandoko.hotPepperAPI.SearchConditionsData
import com.websarva.wings.android.gohandoko.hotPepperAPI.SearchResultsData
import com.websarva.wings.android.gohandoko.hotPepperAPI.ShopInfoAsyncTask
import com.websarva.wings.android.gohandoko.ui.theme.GoHandokoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity(), ShopInfoAsyncTask.ConfirmAsyncListener {

    private val isProgressShowing = mutableStateOf(false)

    val mGenereCdList = arrayListOf<String>()
    val mKeyWordList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GoHandokoTheme {
                if (isProgressShowing.value) {
                    CircularProgressIndicator()
                }
            }
        }

        mGenereCdList.add("G001")

        CoroutineScope(Dispatchers.Main).launch {
            val searchConditionsData = SearchConditionsData(
                lat = 34.663755,
                lng = 135.518540,
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
                "MainActivity",
                "名前: ${gourmet.name}, 住所: ${gourmet.address},画像URL:${gourmet.thumbnail}"
            )
        }
    }

    override fun showProgress() {
        isProgressShowing.value = false
    }

    override fun hideProgress() {
        isProgressShowing.value = true
    }
}