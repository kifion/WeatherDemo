package com.example.weatherapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.DayType
import com.example.weatherapp.domain.model.DayWeather
import com.example.weatherapp.domain.model.WeatherType
import com.example.weatherapp.presentation.Constants
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
            var isSelected = selected == item.dayOfTheWeek
            var drawable = ContextCompat.getDrawable(
                context,
                WeatherType.getDrawableByTypeAndSelect(item.weatherType, isSelected)
            )
            var color = if (isSelected) R.color.white else R.color.dark_blue

            Glide.with(context)
                .load(drawable)
                .centerCrop()
                .into(day_weather_image)

            day_name.text = DayType.getTypeByCode(item.dayOfTheWeek)
            day_temperature.text = item.high.toString() + Constants.DEGREE
            day_name.setTextColor(ContextCompat.getColor(context, color))
            day_temperature.setTextColor(ContextCompat.getColor(context, color))
        }
    }

    interface ClickListener {
        fun onItemClicked(clickedElement: DayWeather)
    }
}
