package com.example.weatherapp.presentation.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.*
import com.example.weatherapp.domain.repository.ILocalStateRepository
import com.example.weatherapp.presentation.Constants
import com.example.weatherapp.presentation.home.adapter.DayListAdapter
import com.example.weatherapp.presentation.home.adapter.HourlyListAdapter
import com.example.weatherapp.presentation.radar.RadarActivity
import com.example.weatherapp.presentation.search.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(),
    DayListAdapter.ClickListener {
    companion object {
        const val CITY_KEY = "city"
    }

    val viewModel: HomeActivityViewModel by viewModel()
    val localStateRepository: ILocalStateRepository by inject()

    private val SEARCH_ACTIVITY_REQUEST_CODE = 0
    var details: CityDetails? = null
    var dayListAdapter: DayListAdapter? = null
    var hourlyListAdapter: HourlyListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getCityDetails(CityList("324234", 4058662))

        viewModel.details.observe(this, Observer {
            it?.let {
                details = it
                updateUi(it)
            }
        })

        search_button.setOnClickListener {
            var intent = Intent(this, SearchActivity::class.java);
            startActivityForResult(intent, SEARCH_ACTIVITY_REQUEST_CODE);
        }

        radar_button.setOnClickListener {
            var intent = Intent(this, RadarActivity::class.java).apply {
                this.putExtra(
                    RadarActivity.RADAR_KEY,
                    Radar(details!!.city.name, details!!.city.longitude, details!!.city.latitude)
                )
            }
            startActivity(intent)
        }
    }

    private fun updateUi(details: CityDetails) {
        setHeaderImage(details)
        setHeaderData(details)
        initDaysAdapter(details)
        initHoursAdapter(details)
    }

    private fun setHeaderData(details: CityDetails) {
        city.text = details.city.asciiName + ", " + details.city.code
        date.text = details.city.date
        time.text = details.city.date
        temp.text = "-".toString() + Constants.DEGREE
    }

    private fun setHeaderImage(details: CityDetails) {
        Glide.with(this)
            .load(details.city.imageUrl)
            .centerCrop()
            .into(city_image)
    }

    private fun initDaysAdapter(details: CityDetails, dayOfWeek: Int = 0) {
        dayListAdapter =
            DayListAdapter(this)
        dayListAdapter!!.selected = dayOfWeek
        dayListAdapter!!.items = details.days
        daily_list.adapter = dayListAdapter

        val layoutManager = GridLayoutManager(this, 7)
        daily_list.layoutManager = layoutManager
    }

    private fun initHoursAdapter(details: CityDetails, dayOfWeek: Int = 0) {
        hourlyListAdapter =
            HourlyListAdapter()
        hourlyListAdapter!!.items = details.days[dayOfWeek].hourlyWeather
        hourly_list.adapter = hourlyListAdapter
        hourly_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SEARCH_ACTIVITY_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val selectedCity = data?.getParcelableExtra<CityList>(CITY_KEY)
                    viewModel.getCityDetails(selectedCity!!)
                }
            }
        }
    }

    override fun onItemClicked(clickedElement: DayWeather) {
        initDaysAdapter(details!!, clickedElement.dayOfTheWeek)
        initHoursAdapter(details!!, clickedElement.dayOfTheWeek)
    }
}