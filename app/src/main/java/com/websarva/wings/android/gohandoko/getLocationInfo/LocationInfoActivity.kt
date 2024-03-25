package com.websarva.wings.android.gohandoko.getLocationInfo

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class LocationInfoActivity : ComponentActivity(), LocationListener {
    private lateinit var locationInfomation: LocationInfomation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationInfomation = LocationInfomation(this, this)
        locationInfomation.getLocation()
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
fun locationScreen(latitude: Double = 0.0, longitude: Double = 0.0) {
    Column {
        Text(text = "経度：$longitude")
        Text(text = "緯度：$latitude")

    }
}

