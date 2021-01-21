package com.example.weatherapp.di

import com.example.weatherapp.data.mapper.CityDetailsMapper
import com.example.weatherapp.data.mapper.CityListMapper
import com.example.weatherapp.data.mapper.CityMapper
import com.example.weatherapp.data.network.ApiService
import com.example.weatherapp.data.network.NetworkService
import com.example.weatherapp.data.repository.LocalStateRepository
import com.example.weatherapp.data.repository.NetworkRepository
import com.example.weatherapp.presentation.home.HomeActivityViewModel
import com.example.weatherapp.presentation.radar.RadarActivityViewModel
import com.example.weatherapp.presentation.search.SearchActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { provideRetrofitService() }
}

val viewModelModule = module {
    viewModel { HomeActivityViewModel() }
    viewModel { RadarActivityViewModel() }
    viewModel { SearchActivityViewModel() }
}

val repositoryModule = module {
    factory { LocalStateRepository(get()) }
    single { NetworkRepository() }
}

val mapperModule = module {
    single { CityListMapper() }
    single { CityMapper() }
    single { CityDetailsMapper() }
}

fun provideRetrofitService(): ApiService {
    return NetworkService.retrofitService()
}