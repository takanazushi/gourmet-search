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


class LocationInfomation : ComponentActivity(), LocationListener {
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


    /**
     * 位置情報の取得
     */
    private fun getLocation() {
        Log.d("LocationInfoDebug", "getLocation()の処理が始まりました")

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        //GPSが有効かどうかの確認
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.d("LocationInfoDebug", "ロケーションマネージャーは有効です")
        } else {
            Log.d("LocationInfoDebug", "GPSが無効です")
            //GPS設定画面を開くためのインテントを作成
            val settingsIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            //GPS設定画面を開く
            startActivity(settingsIntent)
        }

        //位置情報に対してのアクセス許可があるかどうかの確認
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                100
            )
            Log.d("LocationInfoDebug", "checkSelfPermissionがfalseです")
            return
        }

        /**
         * 位置情報の更新
         * GPSを使って取得する
         * 更新は1秒おき
         * 50m移動すると更新
         * 更新する毎にonLocationChanged()を呼び出し
         */
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000,
            50f,
            this
        )
    }

    //位置情報を代入
    override fun onLocationChanged(location: Location) {
        Log.d("LocationInfoDebug", "緯度：${location.longitude}")
        Log.d("LocationInfoDebug", "経度：${location.latitude}")
        setContent{
            locationScreen(location.longitude,location.longitude)
        }
    }

}

@Composable
fun locationScreen(latitude: Double = 0.0, longitude: Double = 0.0)
{
    Column {
        Text(text="緯度：$latitude")
        Text(text ="経度：$longitude")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
     GoHandokoTheme{
        locationScreen(0.0,0.0)
    }
}