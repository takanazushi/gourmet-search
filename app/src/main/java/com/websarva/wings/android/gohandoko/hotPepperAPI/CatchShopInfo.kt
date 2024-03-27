package com.websarva.wings.android.gohandoko.hotPepperAPI

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.websarva.wings.android.gohandoko.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class CatchShopInfo(
    private val context: Context,
    private val listener: ShopInfoAsyncTask.ConfirmAsyncListener
) {
    suspend fun callHotPepperAPI(searchConditionsData: SearchConditionsData) {
        val genereParam = searchConditionsData.genreCdList.joinToString("&genre=", "&genre=")
        val keyWordParam = searchConditionsData.keyWordList

        val urlString = buildString {
            append("https://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key=")
            append(BuildConfig.HOT_API_KEY)
            append(genereParam)
            append("&midnight_meal=")
            append(searchConditionsData.midnight)
            append(keyWordParam)
            append("&lunch=")
            append(searchConditionsData.lunch)
            append("&lat=")
            append(searchConditionsData.lat)
            append("&lng=")
            append(searchConditionsData.lng)
            append("&range=")
            append(searchConditionsData.range)
            append("&count=100")
            append("&format=json")
        }.toString()

        val url = URL(urlString)

        Log.d("CatchShopInfo", urlString)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val shopInfoAsyncTask = ShopInfoAsyncTask(listener)
                val result = shopInfoAsyncTask.execute(url)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("CatchShopInfo", e.toString())
                withContext(Dispatchers.Main) {
                    listener.hideProgress()
                    Toast.makeText(
                        context,
                        "接続がタイムアウトになったため、情報取得できませんでした。",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }
}