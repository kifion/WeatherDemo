package com.example.weatherapp.data.mapper

interface Mapper<E, D> {
    fun fromModel(type: E): D
}