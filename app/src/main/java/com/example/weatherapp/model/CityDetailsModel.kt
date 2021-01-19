package com.example.weatherapp.model

data class CityDetailsModel(
    var name: String = "",
    var days: List<DayWeather> = emptyList()
)

data class DayWeather(
    var weatherType: String = "",
    var high: Int = -1,
    var low: Int = -1,
    var dayOfTheWeek: Int = -1,
    var hourlyWeather: List<HourlyWeather> = emptyList()
)

data class HourlyWeather(
    val weatherType: String = "",
    val hour: Int = -1,
    val temperature: Int = -1,
    val humidity: Double = -1.0,
    val windSpeed: Double = -1.0,
    val rainChance: Double = -1.0
)