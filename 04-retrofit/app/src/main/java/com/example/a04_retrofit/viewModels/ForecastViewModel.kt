package com.example.a04_retrofit.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide.init
import com.example.a04_retrofit.models.ForecastResponse
import com.example.a04_retrofit.services.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastViewModel(city:String="Tunis") : ViewModel() {
    private val forecastMutable = MutableLiveData<ForecastResponse>()
    val forecast: LiveData<ForecastResponse> = forecastMutable

    init {
        getForecast(city)
    }


    fun getForecast(city: String = "Tunis") {
        RetrofitHelper.retrofitService.getForecast(city)
            .enqueue(object : Callback<ForecastResponse> {
                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body() as ForecastResponse
                        forecastMutable.value = data
                    }
                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })
    }


}