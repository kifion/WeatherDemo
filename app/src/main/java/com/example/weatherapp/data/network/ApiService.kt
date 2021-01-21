package com.example.weatherapp.data.network

import com.example.weatherapp.data.network.model.CityDetailsResponseNew
import com.example.weatherapp.data.network.model.CityListResponseNew
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/cities")
    suspend fun getCityList(
        @Query("search") search: String?): CityListResponseNew

    @GET("/cities/{cityID}")
    suspend fun getCityDetails(
        @Path("cityID") cityId: String): CityDetailsResponseNew
}