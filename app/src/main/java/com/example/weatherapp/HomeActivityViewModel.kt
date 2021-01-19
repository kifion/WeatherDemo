package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.CityDetailsModel
import com.example.weatherapp.model.CityModel
import com.example.weatherapp.repository.NetworkRepositoryImpl
import kotlinx.coroutines.launch

class HomeActivityViewModel() : ViewModel() {
    var cities = MutableLiveData<CityDetailsModel>()

    var repository = NetworkRepositoryImpl()

    fun getCityDetails(city: CityModel) {
        this.viewModelScope.launch {
            cities.postValue(repository.getCityDetails(city))
        }
    }
}