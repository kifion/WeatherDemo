package com.example.weatherapp.model.network

import com.example.weatherapp.model.CityModel
import com.fasterxml.jackson.annotation.JsonProperty

data class CityListResponseNew(
    @field:JsonProperty("startIndex")
    val startIndex: Int = 0,

    @field:JsonProperty("cities")
    val cities: ArrayList<CitiesItem> = arrayListOf(),

    @field:JsonProperty("totalCitiesFound")
    val totalCitiesFound: Int = 0
)

data class CitiesItem(
    @field:JsonProperty("elevation")
    val elevation: Int = 0,

    @field:JsonProperty("feature code")
    val featureCode: String = "",

    @field:JsonProperty("geonameid")
    val geonameid: Int = 0,

    @field:JsonProperty("timezone")
    val timezone: String = "",

    @field:JsonProperty("latitude")
    val latitude: Double? = null,

    @field:JsonProperty("dem")
    val dem: Int = 0,

    @field:JsonProperty("admin2 code")
    val admin2Code: Int = 0,

    @field:JsonProperty("population")
    val population: Int = 0,

    @field:JsonProperty("alternatenames")
    val alternatenames: String = "",

    @field:JsonProperty("feature class")
    val featureClass: String = "",

    @field:JsonProperty("cc2")
    val cc2: String = "",

    @field:JsonProperty("admin3 code")
    val admin3Code: Any? = null,

    @field:JsonProperty("imageURLs")
    val imageURLs: ImageURLs? = null,

    @field:JsonProperty("name")
    val name: String = "",

    @field:JsonProperty("admin1 code")
    val admin1Code: String = "",

    @field:JsonProperty("country code")
    val countryCode: String = "",

    @field:JsonProperty("admin4 code")
    val admin4Code: String = "",

    @field:JsonProperty("asciiname")
    val asciiname: String = "",

    @field:JsonProperty("modification date")
    val modificationDate: String = "",

    @field:JsonProperty("longitude")
    val longitude: Double? = null
) {
    fun toCityModel(): CityModel {
        return CityModel(
            this.name,
            this.geonameid
        )
    }
}