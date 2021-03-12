package com.android.lowes.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.lowes.R
import com.android.lowes.activities.WeatherDetailActivity
import com.android.lowes.adapters.OnWeatherListener
import com.android.lowes.adapters.WeatherRecyclerAdapter
import com.android.lowes.viewmodels.WeatherListViewModel

class WeatherListActivity : AppCompatActivity(), OnWeatherListener {
    private var mWeatherListViewModel: WeatherListViewModel? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: WeatherRecyclerAdapter? = null
    private var weatherQuery: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)
        mWeatherListViewModel = ViewModelProviders.of(this).get(WeatherListViewModel::class.java)
        val bundle = intent.extras
        weatherQuery = bundle!!.getString("query")
        mRecyclerView = findViewById(R.id.weather_list)
        testRetrofitRequest()
        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        mWeatherListViewModel!!.weathers.observe(this, Observer { weatherDetails ->
            if (weatherDetails != null) {
                for (weather in weatherDetails) {
                    Log.d(TAG, "onChanged: " + weather.weather)
                    Log.d(TAG, "onChanged: " + weather.main)
                }
                mAdapter!!.setWeather(weatherDetails)
            }
        })
    }

    private fun initRecyclerView() {
        mAdapter = WeatherRecyclerAdapter(this)
        mRecyclerView!!.adapter = mAdapter
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)
    }

    private fun searchWeathersApi(query: String?) {
        mWeatherListViewModel!!.searchWeathersApi(query)
    }

    private fun testRetrofitRequest() {
        searchWeathersApi(weatherQuery)
    }

    override fun onWeatherClick(position: Int) {
        val intent = Intent(this, WeatherDetailActivity::class.java)
        intent.putExtra("temp", mAdapter!!.getSelectedWeather(position)?.main?.getTemp()?.toString())
        intent.putExtra("feelsLike", mAdapter!!.getSelectedWeather(position)?.main?.feelsLike.toString())
        intent.putExtra("main", mAdapter?.getSelectedWeather(position)?.weather?.get(0)?.main)
        intent.putExtra("description", mAdapter!!.getSelectedWeather(position)?.weather?.get(0)?.description)

        startActivity(intent)
    }

    companion object {
        private const val TAG = "WeatherListActivity"
    }
}