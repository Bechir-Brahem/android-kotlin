package com.example.a04_retrofit.services

import com.example.a04_retrofit.models.ForecastResponse
import com.example.a04_retrofit.models.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather?APPID=17db59488cadcad345211c36304a9266")
    fun getWeather(@Query("q") city:String): Call<WeatherResponse>

    @GET("forecast/daily?APPID=17db59488cadcad345211c36304a9266&units=metric&cnt=16")
    fun getForecast(@Query("q") city:String): Call<ForecastResponse>
}
