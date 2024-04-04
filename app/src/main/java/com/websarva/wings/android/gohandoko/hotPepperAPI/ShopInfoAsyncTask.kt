package com.websarva.wings.android.gohandoko.hotPepperAPI

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class ShopInfoAsyncTask(private val listener: ConfirmAsyncListener) {

    private val buffer = StringBuffer()
    private val TAG = "ShopInfoAsyncTask"

    private var totalCount: Int = 0

    fun execute(url: URL) {
        listener.showProgress()

        CoroutineScope(Dispatchers.IO).launch {
            val result = doInBackGround(url)
            withContext(Dispatchers.Main) {
                onPostExecute(result)
            }
        }
    }

    private suspend fun doInBackGround(url: URL): String {
        var con: HttpsURLConnection? = null

        try {
            con = url.openConnection() as HttpsURLConnection
            con.requestMethod = "GET"
            con.connectTimeout = 3000
            con.readTimeout = 3000
            con.connect()
            val resCd = con.responseCode

            if (resCd != HttpsURLConnection.HTTP_OK) {
                throw IndexOutOfBoundsException("HTTP responseCode:$resCd")
            }

            con.inputStream.bufferedReader().use { reader ->
                var line = reader.readLine()
                while (line != null) {
                    buffer.append(line)
                    line = reader.readLine()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(TAG, e.toString())
        } finally {
            con?.disconnect()
        }

        Log.d(TAG, buffer.toString())

        return buffer.toString()
    }

    private fun onPostExecute(result: String) {

        try {
            val jsonObject = JSONObject(result)
            totalCount = jsonObject.getJSONObject("results").getInt("results_available")
            val jsonArray = jsonObject.getJSONObject("results").getJSONArray("shop")
            val searchResultsDataArray = ArrayList<SearchResultsData>()

            for (i in 0 until jsonArray.length()) {
                val json = jsonArray.getJSONObject(i)
                val searchResultsData=SearchResultsData(
                    id = json.getString("id"),
                    name=json.getString("name"),
                    address = json.getString("address"),
                    lat = json.getDouble("lat"),
                    lng = json.getDouble("lng"),
                    lunch = json.getString("lunch"),
                    midnight = json.getString("midnight"),
                    url = json.getString("urls"),
                    access = json.getString("mobile_access"),
                    thumbnail = json.getJSONObject("photo").getJSONObject("mobile").getString("s"),
                    photo = json.getJSONObject("photo").getJSONObject("pc").getString("l"),
                    genre=json.getJSONObject("genre").getString("name"),
                    open = json.getString("open"),
                    close = json.getString("close")
                    )
                searchResultsDataArray.add(searchResultsData)
            }

            listener.shopInfoAsyncCallBack(searchResultsDataArray,totalCount)
        }catch (e:JSONException){
            e.printStackTrace()
            Log.d(TAG,e.toString())
        }finally {
            listener.hideProgress()
        }
    }

    interface ConfirmAsyncListener{
        fun shopInfoAsyncCallBack(searchResultsDataArray:ArrayList<SearchResultsData>, totalCount: Int)
        fun showProgress()
        fun hideProgress()
    }
}
