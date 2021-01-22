package com.example.weatherapp.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.CityDetails
import com.example.weatherapp.domain.utils.DatetimeUtils

private const val ARG_CITY_DETAILS = "cityDetails"

class CityViewPagerFragment : Fragment() {
    private var details: CityDetails? = null

    private var mCallback: Callback? = null

    interface Callback {
        fun setCityPagerCurrentPage(page: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCallback = activity as Callback?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            details = it.getParcelable(ARG_CITY_DETAILS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_city_view_pager, container, false)

        details?.let {
            view.findViewById<TextView>(R.id.city_pager).text = it.city.name
            view.findViewById<TextView>(R.id.date_pager).text =
                DatetimeUtils.getDate(it.city.timezone)
            view.findViewById<TextView>(R.id.time_pager).text =
                DatetimeUtils.getTime(it.city.timezone)
            view.findViewById<TextView>(R.id.temp_pager).text = it.city.temperature
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(cityDetails: CityDetails) =
            CityViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_CITY_DETAILS, cityDetails)
                }
            }
    }
}