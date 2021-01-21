package com.example.weatherapp.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.CityListModel
import com.example.weatherapp.data.repository.NetworkRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchActivityViewModel : ViewModel() {
    var cities = MutableLiveData<List<CityListModel>>()
    var job: Job? = null

    var repository =
        NetworkRepository()

    fun getCities(search: String) {
        job?.cancel()
        job = this.viewModelScope.launch {
            delay(200)
            cities.postValue(repository.getCityList(search))
        }
    }
}