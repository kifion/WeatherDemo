package com.example.weatherapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.model.CityModel
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {
    companion object {
        const val CITY_KEY = "city"
    }

    private lateinit var viewModel: HomeActivityViewModel
    private val SEARCH_ACTIVITY_REQUEST_CODE = 0
    //var cityModel: CityModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)

        /*viewModel.cities.observe(this, Observer {
            println(it.cities!!.first()!!.asciiname)
        })*/

        search_button.setOnClickListener {
            var intent = Intent(this, SearchActivity::class.java);
            startActivityForResult(intent, SEARCH_ACTIVITY_REQUEST_CODE);
        }
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
}