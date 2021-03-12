package com.android.lowes.requests

import com.android.lowes.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    private val retrofitBuilder = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    private val retrofit = retrofitBuilder.build()
    val weatherApi = retrofit.create(WeatherApi::class.java)
}