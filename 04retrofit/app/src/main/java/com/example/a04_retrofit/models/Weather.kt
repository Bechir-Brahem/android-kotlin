package com.example.a04_retrofit.models

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)