package com.example.a04_retrofit

import com.example.a04_retrofit.models.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather?APPID=17db59488cadcad345211c36304a9266")
    fun getWeather(@Query("q") city:String): Call<WeatherResponse>
}
