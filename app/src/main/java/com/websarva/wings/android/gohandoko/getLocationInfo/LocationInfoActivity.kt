package com.websarva.wings.android.gohandoko.getLocationInfo

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import androidx.lifecycle.ReportFragment.Companion.reportFragment

class LocationInfoActivity : ComponentActivity(), LocationListener {
    private lateinit var locationInfomation: LocationInfomation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationInfomation = LocationInfomation(this, this)

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            locationInfomation.requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        else{
            locationInfomation.getLocation()
        }


    }

    override fun onLocationChanged(location: Location) {
        Log.d("LocationInfoDebug", "経度：${location.longitude}")
        Log.d("LocationInfoDebug", "緯度：${location.latitude}")
        setContent {
            locationScreen(location.longitude, location.latitude)
        }
    }

}

@Composable
fun locationScreen(longitude: Double = 0.0, latitude: Double = 0.0) {
    Column {
        Text(text = "経度：$longitude")
        Text(text = "緯度：$latitude")

    }
}

