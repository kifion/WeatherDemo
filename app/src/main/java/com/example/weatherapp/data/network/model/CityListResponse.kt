package com.example.weatherapp.data.network.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CityListResponse(
    @field:JsonProperty("startIndex")
    val startIndex: Int = 0,

    @field:JsonProperty("cities")
    val cities: ArrayList<CityResponse> = arrayListOf(),

    @field:JsonProperty("totalCitiesFound")
    val totalCitiesFound: Int = 0
)