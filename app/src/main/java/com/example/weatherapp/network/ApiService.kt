package com.example.weatherapp.network

import com.example.weatherapp.model.CityListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/cities")
    suspend fun getCityList(
        @Query("search") search: String?): CityListResponse

    @GET("/cities")
    suspend fun getCityDetails(
        @Query("cityID") cityId: String): CityListResponse
}