package com.example.weatherapp.repository

import com.example.weatherapp.model.CityDetailsModel
import com.example.weatherapp.model.SearchCityModel
import com.example.weatherapp.network.NetworkService

class NetworkRepositoryImpl : NetworkRepository {
    override suspend fun getCityList(search: String): List<SearchCityModel> {
        var list = arrayListOf<SearchCityModel>()
        NetworkService.retrofitService().getCityList(search).cities.forEach {
            if (it != null) {
                list.add(it.toCityModel())
            }
        }
        return list
    }

    override suspend fun getCityDetails(city: SearchCityModel): CityDetailsModel {
        var response = NetworkService.retrofitService().getCityDetails(city.cityId.toString());
        return response.toCityDetailsModel()
    }
}