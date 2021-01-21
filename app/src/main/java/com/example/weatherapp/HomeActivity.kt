package com.example.weatherapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.weatherapp.model.CityDetailsModel
import com.example.weatherapp.model.DayWeather
import com.example.weatherapp.model.SearchCityModel
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : AppCompatActivity(), DayListAdapter.ClickListener {
    companion object {
        const val CITY_KEY = "city"
    }

    private lateinit var viewModel: HomeActivityViewModel
    private val SEARCH_ACTIVITY_REQUEST_CODE = 0

    var details: CityDetailsModel? = null

    var dayListAdapter: DayListAdapter? = null
    var hourlyListAdapter: HourlyListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)

        viewModel.getCityDetails(SearchCityModel("324234", 4058662))

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
    }

    private fun updateUi(details: CityDetailsModel) {
        setHeaderImage(details)
        setHeaderData(details)
        initDaysAdapter(details)
        initHoursAdapter(details)
    }

    private fun setHeaderData(details: CityDetailsModel) {
        city.text = details.asciiName + ", " + details.code
        date.text = details.date
        time.text = details.date
        temp.text = details.temperature.toString()
    }

    private fun setHeaderImage(details: CityDetailsModel) {
        Glide.with(this)
            .load(details.imageUrl)
            .centerCrop()
            .into(city_image)
    }

    private fun initDaysAdapter(details: CityDetailsModel, dayOfWeek: Int = 0) {
        dayListAdapter = DayListAdapter(this)
        dayListAdapter!!.selected = dayOfWeek
        dayListAdapter!!.items = details.days
        daily_list.adapter = dayListAdapter

        val layoutManager = GridLayoutManager(this, 7)
        daily_list.layoutManager = layoutManager
    }

    private fun initHoursAdapter(details: CityDetailsModel, dayOfWeek: Int = 0) {
        hourlyListAdapter = HourlyListAdapter()
        hourlyListAdapter!!.items = details.days[dayOfWeek].hourlyWeather
        hourly_list.adapter = hourlyListAdapter
        hourly_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SEARCH_ACTIVITY_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val selectedCity = data?.getParcelableExtra<SearchCityModel>(CITY_KEY)
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