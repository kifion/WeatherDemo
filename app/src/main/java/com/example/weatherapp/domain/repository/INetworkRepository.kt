package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.CityDetails
import com.example.weatherapp.domain.model.CityList

interface INetworkRepository {
    suspend fun getCityList(search: String): List<CityList>
    suspend fun getCityDetails(city: CityList): CityDetails
}