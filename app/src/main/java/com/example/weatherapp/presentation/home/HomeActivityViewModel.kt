package com.example.weatherapp.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.NetworkRepository
import com.example.weatherapp.domain.model.CityDetails
import com.example.weatherapp.domain.model.CityList
import com.example.weatherapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class HomeActivityViewModel : BaseViewModel(), KoinComponent {
    var details = MutableLiveData<CityDetails>()

    var repository: NetworkRepository = get()

    fun getCityDetails(city: CityList) {
        this.viewModelScope.launch {
            details.postValue(repository.getCityDetails(city))
        }
    }
}