package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.network.model.CityDetailsResponse
import com.example.weatherapp.data.network.model.CityResponse
import com.example.weatherapp.data.network.model.DayWeatherResponse
import com.example.weatherapp.data.network.model.HourlyWeatherResponse
import com.example.weatherapp.domain.model.*

open class CityDetailsMapper : Mapper<CityDetailsResponse, CityDetails> {
    override fun fromModel(type: CityDetailsResponse): CityDetails {
        var list = ArrayList(type.weather.days.map { DayWeatherMapper().fromModel(it) })
        list.sortBy { it.dayOfTheWeek }
        return CityDetails(
            CityDataMapper().fromModel(type.city),
            list
        )
    }
}

open class CityDataMapper: Mapper<CityResponse, CityData> {
    override fun fromModel(type: CityResponse): CityData {
        return CityData(
            type.name,
            type.asciiname,
            type.featureCode,
            type.modificationDate,
            type.imageURLs.androidImageURLs.xhdpiImageURL,
            type.longitude,
            type.latitude
        )
    }
}

open class DayWeatherMapper: Mapper<DayWeatherResponse, DayWeather> {
    override fun fromModel(type: DayWeatherResponse): DayWeather {
        return DayWeather(
            type.weatherType,
            type.high,
            type.low,
            type.dayOfTheWeek,
            ArrayList(type.hourlyWeather.map { HourlyWeatherMapper().fromModel(it) })
        )
    }
}

open class HourlyWeatherMapper: Mapper<HourlyWeatherResponse, HourlyWeather> {
    override fun fromModel(type: HourlyWeatherResponse): HourlyWeather {
        return HourlyWeather(
            type.weatherType,
            type.hour,
            type.temperature,
            type.humidity,
            type.windSpeed,
            type.rainChance
        )
    }
}