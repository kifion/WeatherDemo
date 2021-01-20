package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.model.HourlyWeather
import kotlinx.android.synthetic.main.recycler_view_day_item.view.*
import kotlinx.android.synthetic.main.recycler_view_hourly_item.view.*
import kotlinx.android.synthetic.main.recycler_view_search_item.view.*

class HourlyListAdapter() :
    RecyclerView.Adapter<HourlyListAdapter.ViewHolder>() {

    var items = ArrayList<HourlyWeather>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycler_view_hourly_item, viewGroup, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: HourlyWeather) = with(itemView) {
            Glide.with(context)
                .load(context.getDrawable(getDrawableRes(item.weatherType)))
                .centerCrop()
                .into(hourly_image)
            hourly_time.text = item.hour.toString()
            hourly_humidity.text = item.humidity.toString()
            hourly_rain_chance.text = item.rainChance.toString()
            hourly_temp.text = item.temperature.toString()
            hourly_wind.text = item.windSpeed.toString()
        }

        fun getDrawableRes(weatherType: String): Int {
            return when(weatherType) {
                "sunny" -> R.drawable.ic_icon_weather_active_ic_sunny_active
                "cloudy" -> R.drawable.ic_icon_weather_active_ic_cloudy_active
                "heavy_rain" -> R.drawable.ic_icon_weather_active_ic_heavy_rain_active
                "light_rain" -> R.drawable.ic_icon_weather_active_ic_light_rain_active
                "partly_cloudy" -> R.drawable.ic_icon_weather_active_ic_partly_cloudy_active
                "snow_sleet" -> R.drawable.ic_icon_weather_active_ic_snow_sleet_active
                else -> R.drawable.ic_icon_weather_active_ic_cloudy_active
            }
        }
    }
}
