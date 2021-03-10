package com.android.lowes.requests;

import com.android.lowes.requests.responses.WeatherSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    // SEARCH
    @GET("forecast")
    Call<WeatherSearchResponse> searchWeather(
            @Query("q") String query,
            @Query("appid") String key
    );
}
