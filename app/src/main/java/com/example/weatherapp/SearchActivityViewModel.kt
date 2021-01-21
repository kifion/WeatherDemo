package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.SearchCityModel
import com.example.weatherapp.repository.NetworkRepositoryImpl
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchActivityViewModel : ViewModel() {
    var cities = MutableLiveData<List<SearchCityModel>>()
    var job: Job? = null

    var repository = NetworkRepositoryImpl()

    fun getCities(search: String) {
        job?.cancel()
        job = this.viewModelScope.launch {
            delay(200)
            cities.postValue(repository.getCityList(search))
        }
    }
}