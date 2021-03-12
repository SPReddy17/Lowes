package com.android.lowes.repository

import androidx.lifecycle.LiveData
import com.android.lowes.models.Weather
import com.android.lowes.models.WeatherDetails
import com.android.lowes.requests.WeatherApiClient

class WeatherRepository private constructor() {
    private val mWeatherApiCLient: WeatherApiClient
    val weathers: LiveData<List<WeatherDetails>?>
        get() = mWeatherApiCLient?.weathers

    fun searchWeathersApi(query: String?) {
        if (query != null) {
            mWeatherApiCLient.searchRecipesApi(query)
        }
    }



    private object HOLDER {
        val INSTANCE = WeatherRepository()
    }

    companion object {
        val instance: WeatherRepository by lazy { HOLDER.INSTANCE }
    }


    init {
        mWeatherApiCLient = WeatherApiClient.instance
    }
}