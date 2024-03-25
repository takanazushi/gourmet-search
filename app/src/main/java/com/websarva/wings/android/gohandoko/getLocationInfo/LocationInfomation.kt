package com.websarva.wings.android.gohandoko.getLocationInfo


import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat


class LocationInfomation : ComponentActivity(), LocationListener {

    /** 緯度 **/
    var latitude: Double? = null

    /** 経度 **/
    var longitude: Double? = null

    /** LocationManagerを保持する変数 **/
    private lateinit var locationManager: LocationManager

    /**
     * 位置情報の許可を求める
     * 許可：getLocationの呼び出し
     * 拒否：トーストメッセージの表示
     */
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                getLocation()
            } else {
                val toast = Toast.makeText(this, "位置情報許可してくれないと検索できないよ！", Toast.LENGTH_LONG)
                toast.show()
            }
        }

    /**
     * 位置情報アクセスの許可があるか確認
     * 許可がない：requestPermissionLauncherの呼び出し
     * 許可がある：getLoactionの呼び出し
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            getLocation()
        }
    }

    private fun getLocation() {

    }

    override fun onLocationChanged(p0: Location) {
        TODO("Not yet implemented")
    }

}