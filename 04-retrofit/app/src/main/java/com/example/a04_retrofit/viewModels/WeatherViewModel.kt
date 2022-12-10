package com.example.a04_retrofit.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a04_retrofit.services.RetrofitHelper
import com.example.a04_retrofit.models.ForecastResponse
import com.example.a04_retrofit.models.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(city: String) : ViewModel() {
    private val weatherMutable = MutableLiveData<WeatherResponseWrapper>();
    val weather: LiveData<WeatherResponseWrapper> = weatherMutable;

    init {
        getWeather(city)
    }


    fun getWeather(city: String) {
        RetrofitHelper.retrofitService.getWeather(city).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body() as WeatherResponse
                    weatherMutable.value = WeatherResponseWrapper(data, null)
                } else {
                    weatherMutable.value = WeatherResponseWrapper(null, "city not found")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("TAG", t.message.toString())
                weatherMutable.value = WeatherResponseWrapper(null, "error")

            }
        })
    }


}