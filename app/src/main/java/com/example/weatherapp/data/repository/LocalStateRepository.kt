package com.example.weatherapp.data.repository

import com.example.weatherapp.data.LocalStateDataHolder
import com.example.weatherapp.domain.DataState
import com.example.weatherapp.domain.model.CityDetails
import com.example.weatherapp.domain.repository.ILocalStateRepository
import org.koin.core.component.KoinComponent

class LocalStateRepository(private val localStateDataHolder: LocalStateDataHolder) : ILocalStateRepository, KoinComponent {
    override fun setState(state: DataState) {
        localStateDataHolder.state = state
    }

    override fun getState(): DataState = localStateDataHolder.state

    override fun addCity(city: CityDetails) {
        if (!localStateDataHolder.cities.map { it.city.name }.contains(city.city.name)) {
            localStateDataHolder.cities.add(city)
        }
    }

    override fun getCities(): MutableList<CityDetails> = localStateDataHolder.cities

    override fun setCurrentCity(city: CityDetails) {
        localStateDataHolder.selectedCity = city
    }

    override fun getCurrentCity(): CityDetails = localStateDataHolder.selectedCity
}