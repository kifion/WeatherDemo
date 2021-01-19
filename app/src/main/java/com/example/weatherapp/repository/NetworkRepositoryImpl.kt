package com.example.weatherapp.repository

import com.example.weatherapp.model.CityDetailsModel
import com.example.weatherapp.model.CityModel
import com.example.weatherapp.model.DayWeather
import com.example.weatherapp.model.HourlyWeather
import com.example.weatherapp.network.NetworkService

class NetworkRepositoryImpl : NetworkRepository {
    override suspend fun getCityList(search: String): List<CityModel> {
        var list = arrayListOf<CityModel>();
        NetworkService.retrofitService().getCityList(search).cities?.let {
            it.forEach {
                if (it != null) {
                    list.add(it.toCityModel())
                }
            }
        }
        return list
    }

    override suspend fun getCityDetails(city: CityModel): CityDetailsModel {
        var days = arrayListOf<DayWeather>();
        NetworkService.retrofitService().getCityDetails(city.cityId.toString()).weather?.let {
            it.days!!.forEach { responseDay ->
                var day = DayWeather()
                var hourlyList = arrayListOf<HourlyWeather>();
                responseDay!!.hourlyWeather!!.forEach {
                    it?.let { responseHour ->
                        var hourly = HourlyWeather(
                            responseHour.weatherType!!,
                            responseHour.hour!!,
                            responseHour.temperature!!,
                            responseHour.humidity!!,
                            responseHour.windSpeed!!,
                            responseHour.rainChance!!
                        )
                        hourlyList.add(hourly)
                    }
                }
                day.weatherType = responseDay.weatherType!!
                day.high = responseDay.high!!
                day.low = responseDay.low!!
                day.dayOfTheWeek = responseDay.dayOfTheWeek!!
                day.hourlyWeather = hourlyList

                days.add(day)
            }
        }
        return CityDetailsModel(city.name, days)
    }
}