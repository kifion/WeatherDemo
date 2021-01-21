package com.example.weatherapp.data.network.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CityDetailsResponse(
    @field:JsonProperty("city")
    val city: CityResponse = CityResponse(),

    @field:JsonProperty("weather")
    val weather: WeatherResponse = WeatherResponse()
)

data class WeatherResponse(
    @field:JsonProperty("days")
    val days: ArrayList<DayWeatherResponse> = arrayListOf(),

    @field:JsonProperty("id")
    val id: Int = 0
)

data class CityResponse(
    @field:JsonProperty("elevation")
    val elevation: Int = 0,

    @field:JsonProperty("feature code")
    val featureCode: String = "",

    @field:JsonProperty("geonameid")
    val geonameid: Int = 0,

    @field:JsonProperty("timezone")
    val timezone: String = "",

    @field:JsonProperty("latitude")
    val latitude: Double = 0.0,

    @field:JsonProperty("admin2 code")
    val admin2Code: Int = 0,

    @field:JsonProperty("dem")
    val dem: Int = 0,

    @field:JsonProperty("population")
    val population: Int = 0,

    @field:JsonProperty("alternatenames")
    val alternatenames: String = "",

    @field:JsonProperty("feature class")
    val featureClass: String = "",

    @field:JsonProperty("cc2")
    val cc2: String = "",

    @field:JsonProperty("admin3 code")
    val admin3Code: Any? = null,

    @field:JsonProperty("imageURLs")
    val imageURLs: ImageURLs = ImageURLs(),

    @field:JsonProperty("country code")
    val countryCode: String = "",

    @field:JsonProperty("name")
    val name: String = "",

    @field:JsonProperty("admin1 code")
    val admin1Code: String = "",

    @field:JsonProperty("admin4 code")
    val admin4Code: String = "",

    @field:JsonProperty("asciiname")
    val asciiname: String = "",

    @field:JsonProperty("modification date")
    val modificationDate: String = "",

    @field:JsonProperty("longitude")
    val longitude: Double = 0.0
)

data class DayWeatherResponse(
    @field:JsonProperty("weatherType")
    val weatherType: String = "",

    @field:JsonProperty("high")
    val high: Int = 0,

    @field:JsonProperty("low")
    val low: Int = 0,

    @field:JsonProperty("dayOfTheWeek")
    val dayOfTheWeek: Int = 0,

    @field:JsonProperty("hourlyWeather")
    val hourlyWeather: ArrayList<HourlyWeatherResponse> = arrayListOf()
)

data class IOSImageURLs(
    @field:JsonProperty("imageURL")
    val imageURL: String = ""
)

data class ImageURLs(
    @field:JsonProperty("androidImageURLs")
    val androidImageURLs: AndroidImageURLsResponse = AndroidImageURLsResponse(),

    @field:JsonProperty("iOSImageURLs")
    val iOSImageURLs: IOSImageURLs = IOSImageURLs()
)

data class HourlyWeatherResponse(
    @field:JsonProperty("weatherType")
    val weatherType: String = "",

    @field:JsonProperty("hour")
    val hour: Int = 0,

    @field:JsonProperty("temperature")
    val temperature: Int = 0,

    @field:JsonProperty("humidity")
    val humidity: Double = 0.0,

    @field:JsonProperty("windSpeed")
    val windSpeed: Int = 0,

    @field:JsonProperty("rainChance")
    val rainChance: Int = 0
)

data class AndroidImageURLsResponse(
    @field:JsonProperty("hdpiImageURL")
    val hdpiImageURL: String = "",

    @field:JsonProperty("xhdpiImageURL")
    val xhdpiImageURL: String = "",

    @field:JsonProperty("mdpiImageURL")
    val mdpiImageURL: String = ""
)
