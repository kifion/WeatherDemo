package com.example.weatherapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.model.SearchCityModel
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity(), SearchListAdapter.ClickListener {
    private lateinit var viewModel: SearchActivityViewModel
    lateinit var searchListAdapter: SearchListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initSearchAdapter()

        viewModel = ViewModelProvider(this).get(SearchActivityViewModel::class.java)

        search_edit_text.doOnTextChanged { text, start, before, count ->
            viewModel.getCities(text.toString())
        }

        viewModel.cities.observe(this, Observer {
            it?.let {
                initSearchAdapter(ArrayList(it))
            }
        })
    }

    private fun initSearchAdapter(list: ArrayList<SearchCityModel> = arrayListOf()) {
        searchListAdapter = SearchListAdapter(this)
        searchListAdapter.items = list
        search_result_list.adapter = searchListAdapter
        search_result_list.layoutManager = LinearLayoutManager(this)
    }

    override fun onItemClicked(clickedElement: SearchCityModel) {
        val intent = Intent()
        intent.putExtra(HomeActivity.CITY_KEY, clickedElement)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}