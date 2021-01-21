package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.network.model.CityListResponse
import com.example.weatherapp.domain.model.CityList

open class CityListMapper: Mapper<CityListResponse, List<CityList>> {
    override fun fromModel(type: CityListResponse): List<CityList> {
        return type.cities.map { CityMapper().fromModel(it) }
    }
}

