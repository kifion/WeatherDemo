package com.example.weatherapp.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.CityList
import com.example.weatherapp.data.repository.NetworkRepositoryImpl
import com.example.weatherapp.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchActivityViewModel : BaseViewModel() {
    var cities = MutableLiveData<List<CityList>>()
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