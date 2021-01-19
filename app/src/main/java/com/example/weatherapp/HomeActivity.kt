package com.example.weatherapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.model.CityDetailsModel
import com.example.weatherapp.model.CityModel
import com.example.weatherapp.model.DayWeather
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*

class HomeActivity : AppCompatActivity(), DayListAdapter.ClickListener {
    companion object {
        const val CITY_KEY = "city"
    }

    private lateinit var viewModel: HomeActivityViewModel
    private val SEARCH_ACTIVITY_REQUEST_CODE = 0

    //var cityModel: CityModel? = null

    var dayListAdapter: DayListAdapter? = null
    var hourlyListAdapter: DayListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)

        viewModel.details.observe(this, Observer {
            it?.let {
                initAdapters(it)
            }
        })

        search_button.setOnClickListener {
            var intent = Intent(this, SearchActivity::class.java);
            startActivityForResult(intent, SEARCH_ACTIVITY_REQUEST_CODE);
        }
    }

    private fun initAdapters(details: CityDetailsModel) {
        dayListAdapter = DayListAdapter(this)
        dayListAdapter!!.items = details.days
        search_result_list.adapter = dayListAdapter
        search_result_list.layoutManager = LinearLayoutManager(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SEARCH_ACTIVITY_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val selectedCity = data?.getParcelableExtra<CityModel>(CITY_KEY)
                    //cityModel = selectedCity
                    viewModel.getCityDetails(selectedCity!!)
                }
            }
        }
    }

    override fun onItemClicked(clickedElement: DayWeather) {
        println(clickedElement.dayOfTheWeek)
    }
}