package com.example.weatherapp.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.CityDetailsModel
import com.example.weatherapp.domain.model.CityListModel
import com.example.weatherapp.data.repository.NetworkRepository
import kotlinx.coroutines.launch

class HomeActivityViewModel() : ViewModel() {
    var details = MutableLiveData<CityDetailsModel>()

    var repository =
        NetworkRepository()

    fun getCityDetails(city: CityListModel) {
        this.viewModelScope.launch {
            details.postValue(repository.getCityDetails(city))
        }
    }
}