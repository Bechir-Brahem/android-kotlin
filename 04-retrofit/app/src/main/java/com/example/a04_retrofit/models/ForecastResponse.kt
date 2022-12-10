package com.example.a04_retrofit.models

data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastItem>,
    val message: Double
)