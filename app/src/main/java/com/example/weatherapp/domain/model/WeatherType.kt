package com.example.weatherapp.domain.model

import com.example.weatherapp.R

enum class WeatherType(val type: String, val drawableInactive: Int, val drawableActive: Int) {
    SUNNY(
        "sunny",
        R.drawable.ic_icon_weather_active_ic_sunny,
        R.drawable.ic_icon_weather_active_ic_sunny_active
    ),
    CLOUDLY(
        "cloudy",
        R.drawable.ic_icon_weather_active_ic_cloudy,
        R.drawable.ic_icon_weather_active_ic_cloudy_active
    ),
    HEAVY_RAIN(
        "heavyRain",
        R.drawable.ic_icon_weather_active_ic_heavy_rain,
        R.drawable.ic_icon_weather_active_ic_heavy_rain_active
    ),
    LIGHT_RAIN(
        "lightRain",
        R.drawable.ic_icon_weather_active_ic_light_rain,
        R.drawable.ic_icon_weather_active_ic_light_rain_active
    ),
    PARTLY_CLOUDLY(
        "partlyCloudy",
        R.drawable.ic_icon_weather_active_ic_partly_cloudy,
        R.drawable.ic_icon_weather_active_ic_partly_cloudy_active
    ),
    SNOW_SLEET(
        "snowSleet",
        R.drawable.ic_icon_weather_active_ic_snow_sleet,
        R.drawable.ic_icon_weather_active_ic_snow_sleet_active
    );

    companion object {
        fun getDrawableActiveByType(weatherType: String): Int {
            return when(weatherType) {
                SUNNY.type -> SUNNY.drawableActive
                CLOUDLY.type -> CLOUDLY.drawableActive
                HEAVY_RAIN.type -> HEAVY_RAIN.drawableActive
                LIGHT_RAIN.type -> LIGHT_RAIN.drawableActive
                PARTLY_CLOUDLY.type -> PARTLY_CLOUDLY.drawableActive
                SNOW_SLEET.type -> SNOW_SLEET.drawableActive
                else -> {
                    throw Exception("Unknown weather type")
                }
            }
        }

        private fun getDrawableInActiveByType(weatherType: String): Int {
            return when(weatherType) {
                SUNNY.type -> SUNNY.drawableInactive
                CLOUDLY.type -> CLOUDLY.drawableInactive
                HEAVY_RAIN.type -> HEAVY_RAIN.drawableInactive
                LIGHT_RAIN.type -> LIGHT_RAIN.drawableInactive
                PARTLY_CLOUDLY.type -> PARTLY_CLOUDLY.drawableInactive
                SNOW_SLEET.type -> SNOW_SLEET.drawableInactive
                else -> {
                    throw Exception("Unknown weather type")
                }
            }
        }

        fun getDrawableByTypeAndSelect(weatherType: String, isSelected: Boolean): Int {
            return if(isSelected) {
                getDrawableActiveByType(weatherType)
            } else {
                getDrawableInActiveByType(weatherType)
            }
        }
    }
}