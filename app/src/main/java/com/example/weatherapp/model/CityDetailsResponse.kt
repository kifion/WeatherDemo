package com.example.weatherapp.model

data class CityDetailsResponse(
	val city: City? = null,
	val weather: Weather? = null
)

data class Weather(
	val days: List<DaysItem?>? = null,
	val id: Int? = null
)

data class DaysItem(
	val weatherType: String? = null,
	val high: Int? = null,
	val low: Int? = null,
	val dayOfTheWeek: Int? = null,
	val hourlyWeather: List<HourlyWeatherItem?>? = null
)

data class HourlyWeatherItem(
	val weatherType: String? = null,
	val hour: Int? = null,
	val temperature: Int? = null,
	val humidity: Double? = null,
	val windSpeed: Double? = null,
	val rainChance: Double? = null
)