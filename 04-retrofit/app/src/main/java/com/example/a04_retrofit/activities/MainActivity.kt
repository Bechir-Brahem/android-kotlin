package com.example.a04_retrofit.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.a04_retrofit.R
import com.example.a04_retrofit.viewModels.WeatherResponseWrapper
import com.example.a04_retrofit.viewModels.WeatherViewModel
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    var validCity: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val humidity = findViewById<TextView>(R.id.humidity)
        val pressure = findViewById<TextView>(R.id.pressure)
        val description = findViewById<TextView>(R.id.description)
        val temperature = findViewById<TextView>(R.id.temperature)
        val editTextCity: EditText = findViewById(R.id.editTextCity)
        val location = findViewById<TextView>(R.id.location)
        val image = findViewById<ImageView>(R.id.imageView)
        val viewModel = WeatherViewModel("Tunis")


        editTextCity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var tmp = "$p0"
                if (tmp.isEmpty()) {
                    tmp = "Tunis"
                }
                viewModel.getWeather(tmp)
                Log.d("ABC", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
            }

            override fun afterTextChanged(s: Editable?) {}
        })


        viewModel.weather.observe(this, Observer { t: WeatherResponseWrapper ->
            if (t.error == null && t.data != null) {
                val data = t.data
                Log.d("PPP", "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP")
                temperature.text = (data.main.temp - 273.15).roundToInt().toString() + "°C"
                description.text = data.weather[0].description
                humidity.text = data.main.humidity.toString()
                pressure.text = data.main.pressure.toString()
                location.text = data.name
                val url = "https://openweathermap.org/img/wn/" + data.weather[0].icon + "@4x.png"
                Glide.with(this).load(url).into(image)
                validCity = true
            } else if (t.error != null) {
                location.text = t.error
                validCity = false
            }

        })

        val forecastButton: Button = findViewById(R.id.forecastButton)
        forecastButton.setOnClickListener { view ->
            if (validCity) {
                val intent = Intent(view.context, ForecastActivity::class.java)
                intent.putExtra("city", location.text)
                startActivity(intent)
            }
        }


    }
}