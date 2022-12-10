package com.example.a04_retrofit.viewModels

import com.example.a04_retrofit.models.ForecastResponse
import com.example.a04_retrofit.models.WeatherResponse

class WeatherResponseWrapper(
    val data: WeatherResponse?,
    val error: String?
)
