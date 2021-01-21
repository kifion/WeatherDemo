package com.example.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityListModel(
    var name: String = "",
    var cityId: Int = -1
): Parcelable