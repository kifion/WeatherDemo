package com.example.weatherapp.data

import com.example.weatherapp.domain.DataState
import org.koin.core.component.KoinComponent

class LocalStateDataHolder: KoinComponent {
    var state: DataState = DataState.EMPTY

    fun resetState() {
        state = DataState.EMPTY
    }
}