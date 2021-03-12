package com.android.lowes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android.lowes.models.WeatherDetails
import com.android.lowes.repository.WeatherRepository

class WeatherListViewModel : ViewModel() {
    private val mWeatherRepository: WeatherRepository
    val weathers: LiveData<List<WeatherDetails>?>
        get() = mWeatherRepository.weathers

    fun searchWeathersApi(query: String?) {
        mWeatherRepository.searchWeathersApi(query)
    }

    init {
        mWeatherRepository = WeatherRepository.instance
    }
}