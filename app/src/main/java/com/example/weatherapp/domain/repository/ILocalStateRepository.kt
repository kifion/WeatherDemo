package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.DataState

interface ILocalStateRepository {
    fun setState(state: DataState)
    fun getState(): DataState
}