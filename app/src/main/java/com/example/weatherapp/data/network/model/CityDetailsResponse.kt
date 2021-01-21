package com.example.weatherapp.data.network.model

import com.example.weatherapp.domain.model.CityDetailsModel
import com.example.weatherapp.domain.model.DayWeather
import com.example.weatherapp.domain.model.HourlyWeather
import com.fasterxml.jackson.annotation.JsonProperty

data class CityDetailsResponseNew(
    @field:JsonProperty("city")
    val city: City = City(),

    @field:JsonProperty("weather")
    val weather: Weather = Weather()
) {
    fun toCityDetailsModel(): CityDetailsModel {
        var days = arrayListOf<DayWeather>();
        this.weather.days.forEach { responseDay ->
            var day = DayWeather()
            var hourlyList = arrayListOf<HourlyWeather>();
            responseDay.hourlyWeather.forEach { responseHour ->
                var hourly = HourlyWeather(
                    responseHour.weatherType,
                    responseHour.hour,
                    responseHour.temperature,
                    responseHour.humidity,
                    responseHour.windSpeed,
                    responseHour.rainChance
                )
                hourlyList.add(hourly)
            }
            day.weatherType = responseDay.weatherType
            day.high = responseDay.high
            day.low = responseDay.low
            day.dayOfTheWeek = responseDay.dayOfTheWeek
            day.hourlyWeather = hourlyList

            days.add(day)
        }

        return CityDetailsModel(
            this.city.name,
            this.city.asciiname,
            this.city.featureCode,
            this.city.modificationDate,
            days.first().hourlyWeather.first().temperature,
            this.city.imageURLs.androidImageURLs.xhdpiImageURL,
            this.city.longitude,
            this.city.latitude,
            days
        );
    }
}

data class Weather(

    @field:JsonProperty("days")
    val days: ArrayList<DaysItem> = arrayListOf(),

    @field:JsonProperty("id")
    val id: Int = 0
)

data class City(

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

data class DaysItem(

    @field:JsonProperty("weatherType")
    val weatherType: String = "",

    @field:JsonProperty("high")
    val high: Int = 0,

    @field:JsonProperty("low")
    val low: Int = 0,

    @field:JsonProperty("dayOfTheWeek")
    val dayOfTheWeek: Int = 0,

    @field:JsonProperty("hourlyWeather")
    val hourlyWeather: ArrayList<HourlyWeatherItem> = arrayListOf()
)

data class IOSImageURLs(

    @field:JsonProperty("imageURL")
    val imageURL: String = ""
)

data class ImageURLs(

    @field:JsonProperty("androidImageURLs")
    val androidImageURLs: AndroidImageURLs = AndroidImageURLs(),

    @field:JsonProperty("iOSImageURLs")
    val iOSImageURLs: IOSImageURLs = IOSImageURLs()
)

data class HourlyWeatherItem(

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

data class AndroidImageURLs(

    @field:JsonProperty("hdpiImageURL")
    val hdpiImageURL: String = "",

    @field:JsonProperty("xhdpiImageURL")
    val xhdpiImageURL: String = "",

    @field:JsonProperty("mdpiImageURL")
    val mdpiImageURL: String = ""
)
