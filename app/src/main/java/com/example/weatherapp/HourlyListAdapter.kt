package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.model.HourlyWeather
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
            hourly_humidity.text = item.humidity.toString()
            hourly_rain_chance.text = item.rainChance.toString()
            hourly_temp.text = item.temperature.toString()
            hourly_time.text = item.hour.toString()
        }
    }
}
