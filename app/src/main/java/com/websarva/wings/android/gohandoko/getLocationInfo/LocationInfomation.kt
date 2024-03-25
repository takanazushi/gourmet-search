package com.websarva.wings.android.gohandoko.getLocationInfo


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.websarva.wings.android.gohandoko.ui.theme.GoHandokoTheme


class LocationInfomation(private val activity: ComponentActivity,private  val locationListener: LocationListener) {
    /** LocationManagerを保持する変数 **/
    private val locationManager=activity.getSystemService(Context.LOCATION_SERVICE)as android.location.LocationManager

    /**
     * 位置情報の許可を求める
     * 許可：getLocationの呼び出し
     * 拒否：トーストメッセージの表示
     */
    private val requestPermissionLauncher =
        activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                getLocation()
            } else {
                val toast = Toast.makeText(activity, "位置情報許可してくれないと検索できないよ！", Toast.LENGTH_LONG)
                toast.show()
            }
        }


    /**
     * 位置情報の取得
     */
    fun getLocation() {
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

    private fun updateLocation(){
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager.requestLocationUpdates(
                android.location.LocationManager.GPS_PROVIDER,
                1000,
                50f,
                locationListener
            )
        }
    }
}
