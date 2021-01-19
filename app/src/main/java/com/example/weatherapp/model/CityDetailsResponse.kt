package com.example.weatherapp.model

data class CityDetailsResponse(
    val city: CityResponse? = null,
    val weather: Weather? = null
)

data class Weather(
	val days: List<DaysItemResponse?>? = null,
	val id: Int? = null
)

data class DaysItemResponse(
	val weatherType: String? = null,
	val high: Int? = null,
	val low: Int? = null,
	val dayOfTheWeek: Int? = null,
	val hourlyWeather: List<HourlyWeatherItemResponse?>? = null
)

data class HourlyWeatherItemResponse(
	val weatherType: String? = null,
	val hour: Int? = null,
	val temperature: Int? = null,
	val humidity: Double? = null,
	val windSpeed: Double? = null,
	val rainChance: Double? = null
)