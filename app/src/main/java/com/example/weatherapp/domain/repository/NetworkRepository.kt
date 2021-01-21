package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.CityDetailsModel
import com.example.weatherapp.domain.model.CityListModel

interface NetworkRepository {
    suspend fun getCityList(search: String): List<CityListModel>
    suspend fun getCityDetails(city: CityListModel): CityDetailsModel
}