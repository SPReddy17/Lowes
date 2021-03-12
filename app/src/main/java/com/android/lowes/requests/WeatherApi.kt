package com.android.lowes.requests

import com.android.lowes.requests.responses.WeatherSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    // SEARCH
    @GET("forecast")
    fun searchWeather(
            @Query("q") query: String?,
            @Query("appid") key: String?
    ): Call<WeatherSearchResponse?>?
}