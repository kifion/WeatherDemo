package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.DataState

interface LocalStateRepository {
    fun setState(state: DataState)
    fun getState(): DataState
}