package com.websarva.wings.android.gohandoko

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.websarva.wings.android.gohandoko.getLocationInfo.LocationInfomation
import com.websarva.wings.android.gohandoko.hotPepperAPI.CatchShopInfo
import com.websarva.wings.android.gohandoko.hotPepperAPI.SearchConditionsData
import com.websarva.wings.android.gohandoko.hotPepperAPI.SearchResultsData
import com.websarva.wings.android.gohandoko.hotPepperAPI.ShopInfoAsyncTask
import com.websarva.wings.android.gohandoko.searchResultScreen.ResultActivity
import com.websarva.wings.android.gohandoko.searchScreen.SearchData
import com.websarva.wings.android.gohandoko.searchScreen.SearchScreen
import com.websarva.wings.android.gohandoko.ui.theme.GoHandokoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity(), ShopInfoAsyncTask.ConfirmAsyncListener, LocationListener {

    //画面遷移を管理するNavHostController
    private lateinit var navHostController: NavHostController

    //Progressbarの状態管理
    private val isProgressShowing = mutableStateOf(false)

    //ユーザー入力値を保持するオブジェクト
    private val serchData = SearchData()

    //位置情報を取得するためのオブジェクト
    private lateinit var locationInfomation: LocationInfomation

    private var searchResultsDataArray: ArrayList<SearchResultsData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //位置情報の取得を開始
        locationInfomation = LocationInfomation(this, this)
        locationInfomation.getLocation()



        setContent {
            GoHandokoTheme {

                //NavControllerのインスタンス作成
                navHostController = rememberNavController()

                //NavHostを設定
                NavHost(navController = navHostController, startDestination = "search_screen") {
                    composable("search_screen") {
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

                    //検索結果のルートを設定
                    composable("result_screen") {
                        ResultActivity(navHostController,searchResultsDataArray)
                    }
                }


            }
        }
    }

    /**検索処理**/
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

            //検索実行
            CatchShopInfo(this@MainActivity, this@MainActivity).callHotPepperAPI(
                searchConditionsData
            )
        }



        Log.d("MainActivity", "検索終わり")

    }

    override fun onLocationChanged(location: Location) {

    }

    /**結果を受け取った時の処理**/
    override fun shopInfoAsyncCallBack(searchResultsDataArrayList: ArrayList<SearchResultsData>) {

        searchResultsDataArray=searchResultsDataArrayList

        for (gourmet in searchResultsDataArrayList) {
            Log.d(
                "api",
                "名前: ${gourmet.name}, 住所: ${gourmet.address},URL:${gourmet.url},ジャンル${gourmet.genre}"
            )
        }

        Log.d("api", "緯度：${locationInfomation.latitude},経度:${locationInfomation.longitude}")

        //結果画面に遷移
        navHostController.navigate("result_screen")
    }

    //Progressbar表示
    override fun showProgress() {
        isProgressShowing.value = true
    }

    //Progressbar非表示
    override fun hideProgress() {
        isProgressShowing.value = false
    }
}