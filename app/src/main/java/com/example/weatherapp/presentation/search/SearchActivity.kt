package com.example.weatherapp.presentation.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.CityList
import com.example.weatherapp.presentation.Constants
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity(), SearchListAdapter.ClickListener {
    val viewModel: SearchActivityViewModel by viewModel()
    lateinit var searchListAdapter: SearchListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initSearchAdapter()

        search_edit_text.doOnTextChanged { text, start, before, count ->
            viewModel.getCities(text.toString())
        }

        close_button.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        viewModel.cities.observe(this, Observer {
            it?.let {
                initSearchAdapter(ArrayList(it))
            }
        })
    }

    private fun initSearchAdapter(list: ArrayList<CityList> = arrayListOf()) {
        searchListAdapter =
            SearchListAdapter(this)
        searchListAdapter.items = list
        search_result_list.adapter = searchListAdapter
        search_result_list.layoutManager = LinearLayoutManager(this)
    }

    override fun onItemClicked(clickedElement: CityList) {
        val intent = Intent()
        intent.putExtra(Constants.CITY_KEY, clickedElement)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}