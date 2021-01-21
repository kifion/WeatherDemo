package com.example.weatherapp.data

import com.example.weatherapp.domain.DataState

class LocalStateDataHolder {
    var state: DataState = DataState.EMPTY

    fun resetState() {
        state = DataState.EMPTY
    }
}