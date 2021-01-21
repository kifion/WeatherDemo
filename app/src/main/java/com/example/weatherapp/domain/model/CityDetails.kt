package com.example.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityDetails(
    var city: CityData = CityData(),
    var days: ArrayList<DayWeather> = arrayListOf()
): Parcelable

@Parcelize
data class CityData(
    var name: String = "",
    var imageUrl: String = "",
    var timezone: String = "",
    var temperature: String = "",
    val longitude: Double = 0.0,
    var latitude: Double = 0.0
): Parcelable

@Parcelize
data class DayWeather(
    var weatherType: String = "",
    var high: Int = -1,
    var low: Int = -1,
    var dayOfTheWeek: Int = -1,
    var hourlyWeather: ArrayList<HourlyWeather> = arrayListOf()
): Parcelable

@Parcelize
data class HourlyWeather(
    val weatherType: String = "",
    val hour: Int = -1,
    val temperature: Int = -1,
    val humidity: Double = -1.0,
    val windSpeed: Int = 0,
    val rainChance: Int = 0
): Parcelable