package com.example.weatherapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchCityModel(
    var name: String = "",
    var cityId: Int = -1
): Parcelable