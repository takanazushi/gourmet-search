package com.websarva.wings.android.gohandoko.getLocationInfo


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity


class LocationInfomation(
    private val activity: ComponentActivity,
    private val locationListener: LocationListener
) {
    /** LocationManagerを保持する変数 **/
    private val locationManager =
        activity.getSystemService(Context.LOCATION_SERVICE) as android.location.LocationManager

    /** 緯度 **/
    var latitude: Double? = null

    /** 経度 **/
    var longitude: Double? = null


    /**
     * 位置情報の許可を求める
     * 許可：getLocationの呼び出し
     * 拒否：トーストメッセージの表示
     */
     val requestPermissionLauncher =
        activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                getLocation()
            } else {
                val toast =
                    Toast.makeText(activity, "位置情報許可してくれないと検索できないよ！", Toast.LENGTH_LONG)
                toast.show()
            }
        }


    /**
     * 位置情報の取得
     */
    fun getLocation() {

        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            updateLocation()
        }
        else{
            val settgIntent=Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            activity.startActivity(settgIntent)
        }

        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            updateLocation()
        }



    }

    /**
     * 位置情報の更新
     */
    private fun updateLocation() {

        //パーミッションが既に得られているかどうかを確認しないとエラーになる？
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("LocationInformation", "GPSで位置情報取得")
            locationManager.requestLocationUpdates(
                android.location.LocationManager.GPS_PROVIDER,
                1000,
                50f,
                object :LocationListener{
                    override fun onLocationChanged(location: Location) {
                        latitude=location.latitude
                        longitude=location.longitude
                        locationListener.onLocationChanged(location)
                    }
                }
            )

            /** GPSで位置情報が取得できなかった場合 **/
            activity.runOnUiThread {
                //10秒以内にGPSで位置情報を取得できなかった場合
                Handler(Looper.getMainLooper()).postDelayed({

                    Log.d("LocationInformation", "ネットワーク経由で位置情報取得")
                    //ネットワークを利用して位置情報を取得する
                    locationManager.requestLocationUpdates(
                        android.location.LocationManager.NETWORK_PROVIDER,
                        1000,
                        50f,
                        object :LocationListener{
                            override fun onLocationChanged(location: Location) {
                                latitude=location.latitude
                                longitude=location.longitude
                                locationListener.onLocationChanged(location)
                            }
                        }
                    )

                }, 10000)
            }

        }
    }
}
