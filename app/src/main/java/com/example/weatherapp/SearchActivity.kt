package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    private lateinit var viewModel: SearchActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        search_edit_text.doOnTextChanged { text, start, before, count ->
            {
                sour
                viewModel.getCities(text.toString())
            }
        }



        viewModel.cities.observe(this, Observer {
            println(it.cities!!.first()!!.asciiname)
        })
    }
}