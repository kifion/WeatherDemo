package com.example.weatherapp.data.repository

import com.example.weatherapp.data.LocalStateDataHolder
import com.example.weatherapp.domain.DataState
import com.example.weatherapp.domain.repository.LocalStateRepository

class LocalStateRepositoryImpl(private val localStateDataHolder: LocalStateDataHolder) : LocalStateRepository {
    override fun setState(state: DataState) {
        localStateDataHolder.state = state
    }

    override fun getState(): DataState = localStateDataHolder.state
}