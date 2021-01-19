package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
            day_name.text = item.dayOfTheWeek.toString()
            day_temperature.text = item.high.toString()
        }
    }

    interface ClickListener {
        fun onItemClicked(clickedElement: DayWeather)
    }
}
