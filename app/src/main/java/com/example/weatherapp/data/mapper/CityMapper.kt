package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.network.model.CityResponse
import com.example.weatherapp.domain.model.CityList

open class CityMapper: Mapper<CityResponse, CityList> {
    override fun fromModel(type: CityResponse): CityList {
        return CityList(
            type.name,
            type.geonameid
        )
    }
}