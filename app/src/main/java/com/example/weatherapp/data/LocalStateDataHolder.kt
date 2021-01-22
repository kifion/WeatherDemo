package com.example.weatherapp.data

import com.example.weatherapp.domain.DataState
import com.example.weatherapp.domain.model.CityDetails
import org.koin.core.component.KoinComponent

class LocalStateDataHolder: KoinComponent {
    var state: DataState = DataState.EMPTY
    var cities: MutableList<CityDetails> = arrayListOf()
    var selectedCity: CityDetails = CityDetails()

    fun resetState() {
        state = DataState.EMPTY
        cities = arrayListOf()
        selectedCity = CityDetails()
    }
}