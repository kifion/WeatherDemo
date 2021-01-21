package com.example.weatherapp.data.repository

import com.example.weatherapp.domain.repository.NetworkRepository
import com.example.weatherapp.domain.model.CityDetailsModel
import com.example.weatherapp.domain.model.CityListModel
import com.example.weatherapp.data.network.NetworkService

class NetworkRepository : NetworkRepository {
    override suspend fun getCityList(search: String): List<CityListModel> {
        var list = arrayListOf<CityListModel>()
        NetworkService.retrofitService().getCityList(search).cities.forEach {
            if (it != null) {
                list.add(it.toCityModel())
            }
        }
        return list
    }

    override suspend fun getCityDetails(city: CityListModel): CityDetailsModel {
        var response = NetworkService.retrofitService().getCityDetails(city.cityId.toString());
        return response.toCityDetailsModel()
    }
}