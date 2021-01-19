package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.CityListResponse
import com.example.weatherapp.network.NetworkService
import kotlinx.coroutines.launch

class HomeActivityViewModel() : ViewModel() {
    var cities = MutableLiveData<CityListResponse>()

    fun getCities() {
        this.viewModelScope.launch {
            cities.postValue(NetworkService.retrofitService().getCityList("Dallas"))
        }
    }
}