package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.DataState
import com.example.weatherapp.domain.model.CityDetails

interface ILocalStateRepository {
    fun setState(state: DataState)
    fun getState(): DataState

    fun addCity(city: CityDetails)
    fun getCities(): MutableList<CityDetails>

    fun setCurrentCity(city: CityDetails)
    fun getCurrentCity(): CityDetails
}