package com.example.weatherapp.model

data class CityListResponse(
	val startIndex: Int? = null,
	val cities: List<CityResponse?>? = null,
	val totalCitiesFound: Int? = null
)

data class CityResponse(
		val elevation: Int? = null,
		val featureCode: String? = null,
		val geonameid: Int? = null,
		val timezone: String? = null,
		val latitude: Double? = null,
		val dem: Int? = null,
		val admin2Code: Int? = null,
		val population: Int? = null,
		val alternatenames: String? = null,
		val featureClass: String? = null,
		val cc2: String? = null,
		val admin3Code: Admin3Code? = null,
		val imageURLs: ImageURLs? = null,
		val admin1Code: String? = null,
		val name: String? = null,
		val countryCode: String? = null,
		val admin4Code: String? = null,
		val asciiname: String? = null,
		val modificationDate: String? = null,
		val longitude: Double? = null
) {
    fun toCityModel(): CityModel {
		return CityModel(this.name!!)
    }
}

data class AndroidImageURLs(
	val hdpiImageURL: String? = null,
	val xhdpiImageURL: String? = null,
	val mdpiImageURL: String? = null
)

data class Admin3Code(
	val any: Any? = null
)

data class IOSImageURLs(
	val imageURL: String? = null
)

data class ImageURLs(
	val androidImageURLs: AndroidImageURLs? = null,
	val iOSImageURLs: IOSImageURLs? = null
)

