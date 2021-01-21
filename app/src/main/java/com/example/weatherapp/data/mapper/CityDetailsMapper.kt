package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.network.model.CityDetailsResponse
import com.example.weatherapp.domain.model.CityDetails
import com.example.weatherapp.domain.model.DayWeather
import com.example.weatherapp.domain.model.HourlyWeather

open class CityDetailsMapper : Mapper<CityDetailsResponse, CityDetails> {
    override fun fromModel(type: CityDetailsResponse): CityDetails {
        var days = arrayListOf<DayWeather>()
        type.weather.days.forEach { responseDay ->
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

        days.sortBy { it.dayOfTheWeek }

        return CityDetails(
            type.city.name,
            type.city.asciiname,
            type.city.featureCode,
            type.city.modificationDate,
            days.first().hourlyWeather.first().temperature,
            type.city.imageURLs.androidImageURLs.xhdpiImageURL,
            type.city.longitude,
            type.city.latitude,
            days
        );
    }
}