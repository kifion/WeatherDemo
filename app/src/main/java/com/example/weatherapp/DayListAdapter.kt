package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.model.DayWeather
import kotlinx.android.synthetic.main.recycler_view_day_item.view.*

class DayListAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<DayListAdapter.ViewHolder>() {

    var items = ArrayList<DayWeather>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycler_view_day_item, viewGroup, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], clickListener)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: DayWeather, clickListener: ClickListener) = with(itemView) {
            setOnClickListener { clickListener.onItemClicked(item) }
            Glide.with(context)
                .load(context.getDrawable(getDrawableRes(item.weatherType)))
                .centerCrop()
                .into(day_weather_image)
            day_name.text = item.dayOfTheWeek.toString()
            day_temperature.text = item.high.toString()
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

    interface ClickListener {
        fun onItemClicked(clickedElement: DayWeather)
    }
}
