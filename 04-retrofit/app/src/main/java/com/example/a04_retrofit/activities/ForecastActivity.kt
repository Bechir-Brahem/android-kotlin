package com.example.a04_retrofit.activities

import WeatherForecastAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a04_retrofit.R
import com.example.a04_retrofit.models.ForecastItem
import com.example.a04_retrofit.models.ForecastResponse
import com.example.a04_retrofit.viewModels.ForecastViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class ForecastActivity : AppCompatActivity() {
    lateinit var viewModel:ForecastViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        var city=intent.getStringExtra("city")
        if (city==null){
            city="Tunis"
        }
        val cityView = findViewById<TextView>(R.id.city)
        cityView.text=city


        viewModel=ForecastViewModel(city)

        viewModel.forecast.observe(this, Observer {
            t: ForecastResponse->

            var weatherListAdapter:WeatherForecastAdapter=WeatherForecastAdapter(t.list as ArrayList<ForecastItem>)
            var rv = findViewById<View>(R.id.recyclerView) as RecyclerView
            rv.adapter = weatherListAdapter
            rv.layoutManager = LinearLayoutManager(this)


        })



    }
}