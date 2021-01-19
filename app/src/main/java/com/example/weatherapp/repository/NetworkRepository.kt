package com.example.weatherapp.repository

import com.example.weatherapp.model.CityDetailsModel
import com.example.weatherapp.model.CityModel

interface NetworkRepository {
    suspend fun getCityList(search: String): List<CityModel>
    suspend fun getCityDetails(city: CityModel): CityDetailsModel
}