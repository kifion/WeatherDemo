package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.model.CityModel
import com.example.weatherapp.model.CityResponse
import kotlinx.android.synthetic.main.recycler_view_search_item.view.*

class SearchListAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    var items = ArrayList<CityModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycler_view_search_item, viewGroup, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], clickListener)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: CityModel, clickListener: ClickListener) = with(itemView) {
            setOnClickListener { clickListener.onItemClicked(item) }
            city_name.text = item.name
        }
    }

    interface ClickListener {
        fun onItemClicked(clickedElement: CityModel)
    }
}
