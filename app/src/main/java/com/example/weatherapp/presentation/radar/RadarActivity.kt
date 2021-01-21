package com.example.weatherapp.presentation.radar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.Radar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class RadarActivity : AppCompatActivity(), OnMapReadyCallback {
    companion object {
        const val RADAR_KEY = "radar_key"
        const val MAP_ZOOM = 9.0F
    }

    private lateinit var mMap: GoogleMap
    private lateinit var radarData: Radar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radar)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        intent.getParcelableExtra<Radar>(RADAR_KEY)?.let {
            radarData = it
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val currentLocation = LatLng(radarData.latitude, radarData.longitude)
        mMap.addMarker(MarkerOptions().position(currentLocation).title(radarData.name))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(MAP_ZOOM))
    }
}