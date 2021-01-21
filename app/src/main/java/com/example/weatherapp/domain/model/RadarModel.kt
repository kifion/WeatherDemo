package com.example.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RadarModel(
    var name: String,
    val longitude: Double,
    var latitude: Double
): Parcelable