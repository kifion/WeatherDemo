package com.example.weatherapp.di

import com.example.weatherapp.data.LocalStateDataHolder
import com.example.weatherapp.data.mapper.CityDetailsMapper
import com.example.weatherapp.data.mapper.CityListMapper
import com.example.weatherapp.data.mapper.CityMapper
import com.example.weatherapp.data.network.ApiService
import com.example.weatherapp.data.network.NetworkService
import com.example.weatherapp.data.repository.LocalStateRepository
import com.example.weatherapp.data.repository.NetworkRepository
import com.example.weatherapp.domain.repository.ILocalStateRepository
import com.example.weatherapp.presentation.home.HomeActivityViewModel
import com.example.weatherapp.presentation.search.SearchActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { provideRetrofitService() }
}

val viewModelModule = module {
    viewModel { HomeActivityViewModel() }
    viewModel { SearchActivityViewModel() }
}

val repositoryModule = module {
    single { provideLocationStateRepository() }
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

fun provideLocationStateRepository(): ILocalStateRepository {
    return LocalStateRepository(LocalStateDataHolder())
}