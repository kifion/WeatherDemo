package com.example.weatherapp.domain.model

data class CityDetails(
    var city: CityData = CityData(),
    var days: ArrayList<DayWeather> = arrayListOf()
)

data class CityData(
    var name: String = "",
    var imageUrl: String = "",
    var timezone: String = "",
    var temperature: String = "",
    val longitude: Double = 0.0,
    var latitude: Double = 0.0
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