package com.example.weatherapp.presentation.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.CityDetails
import com.example.weatherapp.domain.model.CityList
import com.example.weatherapp.domain.model.DayWeather
import com.example.weatherapp.domain.repository.ILocalStateRepository
import com.example.weatherapp.presentation.Constants.Companion.CITY_KEY
import com.example.weatherapp.presentation.Constants.Companion.DAYS_NUMBER
import com.example.weatherapp.presentation.Constants.Companion.SEARCH_ACTIVITY_REQUEST_CODE
import com.example.weatherapp.presentation.home.adapter.CityPagerAdapter
import com.example.weatherapp.presentation.home.adapter.DayListAdapter
import com.example.weatherapp.presentation.home.adapter.HourlyListAdapter
import com.example.weatherapp.presentation.radar.RadarActivity
import com.example.weatherapp.presentation.search.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), DayListAdapter.ClickListener,
    CityViewPagerFragment.Callback {
    val viewModel: HomeActivityViewModel by viewModel()
    val localStateRepository: ILocalStateRepository by inject()

    var dayListAdapter: DayListAdapter? = null
    var hourlyListAdapter: HourlyListAdapter? = null
    private lateinit var pagerAdapter: CityPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewPager(localStateRepository.getCities())

        viewModel.details.observe(this, Observer {
            it?.let {
                addNewCity(it)
            }
        })

        weather_pager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                selectCity(localStateRepository.getCities()[position])
            }

            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
        })

        search_button.setOnClickListener { openSearchActivity() }
        radar_button.setOnClickListener { openRadarActivity() }
    }

    fun addNewCity(cityDetails: CityDetails) {
        localStateRepository.setCurrentCity(cityDetails)
        localStateRepository.addCity(cityDetails)
        updateUi(cityDetails)
        setCityPagerCurrentPage(localStateRepository.getCities().size - 1)
    }

    fun selectCity(cityDetails: CityDetails) {
        localStateRepository.setCurrentCity(cityDetails)
        setHeaderImage(cityDetails)
        initDaysAdapter(cityDetails)
        initHoursAdapter(cityDetails)
    }

    fun initViewPager(cities: MutableList<CityDetails>) {
        pagerAdapter = CityPagerAdapter(supportFragmentManager, cities)
        weather_pager.adapter = pagerAdapter
    }

    private fun updateUi(details: CityDetails) {
        setHeaderImage(details)
        initDaysAdapter(details)
        initHoursAdapter(details)
        initViewPager(localStateRepository.getCities())
    }

    private fun setHeaderImage(details: CityDetails) {
        Glide.with(this)
            .load(details.city.imageUrl)
            .centerCrop()
            .into(city_image)
    }

    private fun initDaysAdapter(details: CityDetails, dayOfWeek: Int = 0) {
        dayListAdapter = DayListAdapter(this)
        dayListAdapter?.let {
            it.selected = dayOfWeek
            it.items = details.days
            daily_list.adapter = it
            daily_list.layoutManager = GridLayoutManager(this, DAYS_NUMBER)
        }
    }

    private fun initHoursAdapter(details: CityDetails, dayOfWeek: Int = 0) {
        hourlyListAdapter = HourlyListAdapter()
        hourlyListAdapter?.let {
            it.items = details.days[dayOfWeek].hourlyWeather
            hourly_list.adapter = it
            hourly_list.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onDayItemClicked(dayWeather: DayWeather) {
        initDaysAdapter(localStateRepository.getCurrentCity(), dayWeather.dayOfTheWeek)
        initHoursAdapter(localStateRepository.getCurrentCity(), dayWeather.dayOfTheWeek)
    }

    override fun setCityPagerCurrentPage(page: Int) {
        weather_pager.currentItem = page
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SEARCH_ACTIVITY_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val selectedCity = data?.getParcelableExtra<CityList>(CITY_KEY)
                    selectedCity?.let {
                        viewModel.getCityDetails(selectedCity)
                    }
                }
            }
        }
    }

    fun openSearchActivity() {
        startActivityForResult(
            Intent(this, SearchActivity::class.java),
            SEARCH_ACTIVITY_REQUEST_CODE
        );
    }

    fun openRadarActivity() {
        if (localStateRepository.getCurrentCity().city.name.isNotEmpty()) {
            startActivity(Intent(this, RadarActivity::class.java))
        }
    }
}