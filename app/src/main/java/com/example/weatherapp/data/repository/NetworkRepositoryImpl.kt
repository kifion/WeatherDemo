package com.example.weatherapp.data.repository

import com.example.weatherapp.domain.repository.NetworkRepository
import com.example.weatherapp.domain.model.CityDetails
import com.example.weatherapp.domain.model.CityList
import com.example.weatherapp.data.network.NetworkService

class NetworkRepositoryImpl : NetworkRepository {
    override suspend fun getCityList(search: String): List<CityList> {
        var list = arrayListOf<CityList>()
        NetworkService.retrofitService().getCityList(search).cities.forEach {
            if (it != null) {
                list.add(it.toCityModel())
            }
        }
        return list
    }

    override suspend fun getCityDetails(city: CityList): CityDetails {
        var response = NetworkService.retrofitService().getCityDetails(city.cityId.toString());
        return response.toCityDetailsModel()
    }
}