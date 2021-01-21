package com.example.weatherapp.data.repository

import com.example.weatherapp.data.LocalStateDataHolder
import com.example.weatherapp.domain.DataState
import com.example.weatherapp.domain.repository.ILocalStateRepository
import org.koin.core.component.KoinComponent

class LocalStateRepository(private val localStateDataHolder: LocalStateDataHolder) : ILocalStateRepository, KoinComponent {
    override fun setState(state: DataState) {
        localStateDataHolder.state = state
    }

    override fun getState(): DataState = localStateDataHolder.state
}