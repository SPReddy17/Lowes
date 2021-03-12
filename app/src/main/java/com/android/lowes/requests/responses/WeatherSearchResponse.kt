package com.android.lowes.requests.responses

import com.android.lowes.models.WeatherDetails
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherSearchResponse {
    @SerializedName("cnt")
    @Expose
    val count = 0

    @SerializedName("list")
    @Expose
    val weathers: List<WeatherDetails>? = null

    override fun toString(): String {
        return "WeatherSearchResponse{" +
                "count=" + count +
                ", weathers=" + weathers +
                '}'
    }
}