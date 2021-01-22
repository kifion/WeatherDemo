package com.example.weatherapp.presentation.radar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.domain.repository.ILocalStateRepository
import com.example.weatherapp.presentation.Constants.Companion.MAP_ZOOM
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.ext.android.inject

class RadarActivity : AppCompatActivity(), OnMapReadyCallback {
    val localStateRepository: ILocalStateRepository by inject()

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radar)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        var details = localStateRepository.getCurrentCity()

        mMap = googleMap
        val currentLocation = LatLng(details.city.latitude, details.city.longitude)
        mMap.addMarker(MarkerOptions().position(currentLocation).title(details.city.name))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(MAP_ZOOM))
    }
}