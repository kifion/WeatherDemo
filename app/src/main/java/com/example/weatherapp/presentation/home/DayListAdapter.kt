package com.example.weatherapp.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.DayWeather
import kotlinx.android.synthetic.main.recycler_view_day_item.view.*

class DayListAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<DayListAdapter.ViewHolder>() {

    var items = ArrayList<DayWeather>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var selected = 0

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(
                    R.layout.recycler_view_day_item,
                    viewGroup,
                    false
                )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], selected, clickListener)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(
            item: DayWeather,
            selected: Int,
            clickListener: ClickListener
        ) = with(itemView) {
            setOnClickListener { clickListener.onItemClicked(item) }

            var drawable = ContextCompat.getDrawable(context, getDrawableRes(item.weatherType, selected == item.dayOfTheWeek))

            var color: Int = if (selected == item.dayOfTheWeek) {
                R.color.white
            } else {
                R.color.dark_blue
            }

            Glide.with(context)
                .load(drawable)
                .centerCrop()
                .into(day_weather_image)

            day_name.text = getDayById(item.dayOfTheWeek)
            day_temperature.text = item.high.toString()
            day_name.setTextColor(getResources().getColor(color))
            day_temperature.setTextColor(getResources().getColor(color))
        }

        fun getDrawableRes(weatherType: String, isSelected: Boolean): Int {
            return when {
                weatherType == "sunny" && !isSelected -> R.drawable.ic_icon_weather_active_ic_sunny
                weatherType == "sunny" && isSelected -> R.drawable.ic_icon_weather_active_ic_sunny_active
                weatherType == "cloudy" && !isSelected -> R.drawable.ic_icon_weather_active_ic_cloudy
                weatherType == "cloudy" && isSelected -> R.drawable.ic_icon_weather_active_ic_cloudy_active
                weatherType == "heavyRain" && !isSelected -> R.drawable.ic_icon_weather_active_ic_heavy_rain
                weatherType == "heavyRain" && isSelected -> R.drawable.ic_icon_weather_active_ic_heavy_rain_active
                weatherType == "lightRain" && !isSelected -> R.drawable.ic_icon_weather_active_ic_light_rain
                weatherType == "lightRain" && isSelected -> R.drawable.ic_icon_weather_active_ic_light_rain_active
                weatherType == "partlyCloudy" && !isSelected -> R.drawable.ic_icon_weather_active_ic_partly_cloudy
                weatherType == "partlyCloudy" && isSelected -> R.drawable.ic_icon_weather_active_ic_partly_cloudy_active
                weatherType == "snowSleet" && !isSelected -> R.drawable.ic_icon_weather_active_ic_snow_sleet
                weatherType == "snowSleet" && isSelected -> R.drawable.ic_icon_weather_active_ic_snow_sleet_active
                else -> {
                    throw Exception("Unknown weather type")
                }
            }
        }

        fun getDayById(dayOfTheWeek: Int): String {
            return when (dayOfTheWeek) {
                0 -> "Mon"
                1 -> "Tue"
                2 -> "Wen"
                3 -> "Thu"
                4 -> "Fri"
                5 -> "Sat"
                6 -> "Sun"
                else -> {
                    throw Exception("Unknown day of week")
                }
            }
        }
    }

    interface ClickListener {
        fun onItemClicked(clickedElement: DayWeather)
    }
}
