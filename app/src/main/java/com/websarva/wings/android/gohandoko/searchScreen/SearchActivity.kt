package com.websarva.wings.android.gohandoko

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.websarva.wings.android.gohandoko.detailsScreen.DetealsScreen
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

class SearchActivity : ComponentActivity(), ShopInfoAsyncTask.ConfirmAsyncListener, LocationListener {

    //画面遷移を管理するNavHostController
    private lateinit var navHostController: NavHostController

    //Progressbarの状態管理
    private val isProgressShowing = mutableStateOf(false)

    //ユーザー入力値を保持するオブジェクト
    private val serchData = SearchData()

    //位置情報を取得するためのオブジェクト
    private lateinit var locationInfomation: LocationInfomation

    private var searchResultsDataArray: ArrayList<SearchResultsData> = ArrayList()

    val selectedData = mutableStateOf<SearchResultsData?>(null)

    private var shopCount: Int = 0

    //位置情報取得中かどうかflag
    private val isGettingLocation= mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //位置情報の取得を開始
        locationInfomation = LocationInfomation(this, this)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            locationInfomation.requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            locationInfomation.getLocation()
        }

        setContent {
            GoHandokoTheme {

                //位置情報がまだ取得できてなかったら
                if(locationInfomation.latitude==null)
                {
                    //flagをTrueにする
                    isGettingLocation.value=true
                }

                //位置情報が取得できていなかったら
                if (isGettingLocation.value) {
                    //位置情報を取得中であることを表示する
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {},
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator()
                            Text(text = "位置情報取得中……")
                        }
                    }
                } else {
                    //位置情報を取得できたら、処理開始

                    //NavControllerのインスタンス作成
                    navHostController = rememberNavController()

                    //NavHostを設定
                    NavHost(navController = navHostController, startDestination = "search_screen") {
                        composable("search_screen") {
                            if (isProgressShowing.value) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .pointerInput(Unit) {},
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        CircularProgressIndicator()
                                        Text(text = "お店の情報取得中……")
                                    }
                                }
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
                            ResultActivity(navHostController, searchResultsDataArray, selectedData,shopCount)
                        }

                        composable("detail_screen") {
                            if (selectedData.value != null) {
                                DetealsScreen(
                                    navController = navHostController,
                                    data = selectedData.value!!
                                )
                            }
                        }
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
            CatchShopInfo(this@SearchActivity, this@SearchActivity).callHotPepperAPI(
                searchConditionsData
            )
        }



        Log.d("MainActivity", "検索終わり")

    }

    override fun onLocationChanged(location: Location) {

        if(locationInfomation.latitude!=null){
            isGettingLocation.value=false
        }
    }

    /**結果を受け取った時の処理**/
    override fun shopInfoAsyncCallBack(searchResultsDataArrayList: ArrayList<SearchResultsData>,totalCount: Int) {

        searchResultsDataArray = searchResultsDataArrayList
        shopCount=totalCount

        for (gourmet in searchResultsDataArrayList) {
            Log.d(
                "api",
                "名前: ${gourmet.name}, 住所: ${gourmet.address},URL:${gourmet.url},ジャンル${gourmet.genre}"
            )
        }

        Log.d("api", "緯度：${locationInfomation.latitude},経度:${locationInfomation.longitude}")
        Log.d("api", "見せの数：${shopCount}")

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