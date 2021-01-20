package com.example.weatherapp.model

import android.graphics.drawable.Drawable

data class CityDetailsModel(
    var name: String = "",
    var asciiName: String = "",
    var code: String = "",
    var date: String = "",
    var temperature: Int = 0,
    var imageUrl: String = "https://i.stack.imgur.com/y9DpT.jpg",
    var days: ArrayList<DayWeather> = arrayListOf()
)

data class DayWeather(
    var weatherType: String = "",
    var high: Int = -1,
    var low: Int = -1,
    var dayOfTheWeek: Int = -1,
    var hourlyWeather: ArrayList<HourlyWeather> = arrayListOf()
)

data class HourlyWeather(
    val weatherType: String = "",
    val hour: Int = -1,
    val temperature: Int = -1,
    val humidity: Double = -1.0,
    val windSpeed: Int = 0,
    val rainChance: Int = 0
)