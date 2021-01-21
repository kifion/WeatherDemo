package com.example.weatherapp.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.CityDetails
import com.example.weatherapp.domain.model.CityList
import com.example.weatherapp.data.repository.NetworkRepositoryImpl
import kotlinx.coroutines.launch

class HomeActivityViewModel() : ViewModel() {
    var details = MutableLiveData<CityDetails>()

    var repository =
        NetworkRepositoryImpl()

    fun getCityDetails(city: CityList) {
        this.viewModelScope.launch {
            details.postValue(repository.getCityDetails(city))
        }
    }
}