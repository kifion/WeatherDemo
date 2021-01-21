package com.example.weatherapp.presentation.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.weatherapp.domain.model.CityDetails
import com.example.weatherapp.presentation.home.CityViewPagerFragment

class CityPagerAdapter(
    fragmentManager: FragmentManager,
    private val cities: MutableList<CityDetails>
) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return CityViewPagerFragment.newInstance(cities[position])
    }

    override fun getCount(): Int {
        return cities.size
    }
}