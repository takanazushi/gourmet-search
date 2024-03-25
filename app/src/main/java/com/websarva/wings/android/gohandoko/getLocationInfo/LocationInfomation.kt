package com.websarva.wings.android.gohandoko.getLocationInfo


import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts


class LocationInfomation : ComponentActivity(), LocationListener {
    var latitude: Double? = null
    var longitude: Double? = null

    private lateinit var locationManager: LocationManager

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                locationStart()
            } else {
                val toast = Toast.makeText(this, "位置情報許可してくれないと検索できないよ！", Toast.LENGTH_LONG)
                toast.show()
            }
        }

    private fun locationStart() {

    }

    override fun onLocationChanged(p0: Location) {
        TODO("Not yet implemented")
    }

}