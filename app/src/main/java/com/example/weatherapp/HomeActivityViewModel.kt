package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.CityDetailsModel
import com.example.weatherapp.model.CityModel
import com.example.weatherapp.repository.NetworkRepositoryImpl
import kotlinx.coroutines.launch

class HomeActivityViewModel() : ViewModel() {
    var details = MutableLiveData<CityDetailsModel>()

    var repository = NetworkRepositoryImpl()

    fun getCityDetails(city: CityModel) {
        this.viewModelScope.launch {
            details.postValue(repository.getCityDetails(city))
        }
    }
}