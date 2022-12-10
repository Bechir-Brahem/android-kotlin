import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a04_retrofit.R
import com.example.a04_retrofit.models.ForecastItem
import java.text.SimpleDateFormat
import java.util.*

class WeatherForecastAdapter(var forecasts: ArrayList<ForecastItem>) :
    RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.row_imageView)
        val temperature = itemView.findViewById<TextView>(R.id.row_temperature)
        val pressure = itemView.findViewById<TextView>(R.id.row_pressure)
        val humidity = itemView.findViewById<TextView>(R.id.row_humidity)
        val date = itemView.findViewById<TextView>(R.id.date)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherForecastAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.row_weather_forecast, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: WeatherForecastAdapter.ViewHolder, position: Int) {
        val forecast: ForecastItem = forecasts.get(position)
        val temperature = viewHolder.temperature
        val imageView = viewHolder.imageView
        temperature.text = forecast.temp.day.toInt().toString() + "°C"
        viewHolder.humidity.text = forecast.humidity.toString()
        viewHolder.pressure.text = forecast.pressure.toString()
        val url = "https://openweathermap.org/img/wn/" + forecast.weather[0].icon + "@4x.png"
        Glide.with(viewHolder.itemView).load(url).into(imageView)
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val netDate = Date(forecast.dt.toLong() * 1000)
        viewHolder.date.text = sdf.format(netDate)


    }

    override fun getItemCount(): Int {
        return forecasts.size
    }


}