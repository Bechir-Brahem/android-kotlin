package com.example.a04_retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a04_retrofit.models.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel:ViewModel() {
    private val weatherMutable = MutableLiveData<WeatherResponse>();
    val weather : LiveData<WeatherResponse> = weatherMutable;
    init{
        getWeather()
    }

    private fun getWeather() {
        RetrofitHelper.retrofitService.getWeather().enqueue(object: Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ){
                if(response.isSuccessful)
                    weatherMutable.value= response.body() as WeatherResponse
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }


}