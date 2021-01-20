package com.example.weatherapp.repository

import com.example.weatherapp.model.CityDetailsModel
import com.example.weatherapp.model.CityModel
import com.example.weatherapp.model.DayWeather
import com.example.weatherapp.model.HourlyWeather
import com.example.weatherapp.network.NetworkService

class NetworkRepositoryImpl : NetworkRepository {
    override suspend fun getCityList(search: String): List<CityModel> {
        var list = arrayListOf<CityModel>()
        NetworkService.retrofitService().getCityList(search).cities.forEach {
            if (it != null) {
                list.add(it.toCityModel())
            }
        }
        return list
    }

    override suspend fun getCityDetails(city: CityModel): CityDetailsModel {
        var response = NetworkService.retrofitService().getCityDetails(city.cityId.toString());
        return response.toCityDetailsModel()
    }
}