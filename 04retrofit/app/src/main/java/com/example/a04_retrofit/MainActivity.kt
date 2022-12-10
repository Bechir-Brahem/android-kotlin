package com.example.a04_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.a04_retrofit.models.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val humidity = findViewById<TextView>(R.id.humidity)
        val pressure = findViewById<TextView>(R.id.pressure)
        val description = findViewById<TextView>(R.id.description)
        val temperature = findViewById<TextView>(R.id.temperature)


        val viewModel = WeatherViewModel()
        viewModel.weather.observe(this, Observer { t: WeatherResponse ->
            temperature.setText((t.main.temp - 273.15).roundToInt().toString() + "°C")
            description.setText(t.weather[0].description)
            humidity.setText(t.main.humidity.toString())
            pressure.setText(t.main.pressure.toString())
        })

    }
}