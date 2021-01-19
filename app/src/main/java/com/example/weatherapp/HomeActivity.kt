package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)

        //viewModel.getCities()

        /*viewModel.cities.observe(this, Observer {
            println(it.cities!!.first()!!.asciiname)
        })*/

        search_button.setOnClickListener {
            var intent = Intent(this, SearchActivity::class.java);
            startActivity(intent);
        }
    }
}