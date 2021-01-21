package com.example.weatherapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.HourlyWeather
import com.example.weatherapp.domain.model.WeatherType
import com.example.weatherapp.presentation.Constants
import kotlinx.android.synthetic.main.recycler_view_hourly_item.view.*

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
                .inflate(
                    R.layout.recycler_view_hourly_item,
                    viewGroup,
                    false
                )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: HourlyWeather) = with(itemView) {
            Glide.with(context)
                .load(WeatherType.getDrawableActiveByType(item.weatherType))
                .centerCrop()
                .into(hourly_image)

            hourly_time.text = item.hour.toString()
            hourly_humidity.text = item.humidity.toString()
            hourly_rain_chance.text = item.rainChance.toString()
            hourly_temp.text = item.temperature.toString() + Constants.DEGREE
            hourly_wind.text = item.windSpeed.toString()
        }
    }
}
