package com.example.weatherapp.data.repository

import com.example.weatherapp.data.mapper.CityDetailsMapper
import com.example.weatherapp.data.mapper.CityListMapper
import com.example.weatherapp.data.network.ApiService
import com.example.weatherapp.domain.model.CityDetails
import com.example.weatherapp.domain.model.CityList
import com.example.weatherapp.domain.repository.INetworkRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NetworkRepository : INetworkRepository, KoinComponent {
    private val retrofit: ApiService by inject()
    private val cityListMapper: CityListMapper by inject()
    private val cityDetailsMapper: CityDetailsMapper by inject()

    override suspend fun getCityList(search: String): List<CityList> {
        var response = retrofit.getCityList(search)
        return cityListMapper.fromModel(response)
    }

    override suspend fun getCityDetails(city: CityList): CityDetails {
        var response = retrofit.getCityDetails(city.cityId.toString());
        return cityDetailsMapper.fromModel(response)
    }
}