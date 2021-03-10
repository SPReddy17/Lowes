package com.android.lowes.requests.responses;

import com.android.lowes.models.WeatherDetails;
import com.android.lowes.models.WeatherResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherSearchResponse {

    @SerializedName("cnt")
    @Expose()
    private int  count;

    @SerializedName("list")
    @Expose()
    private List<WeatherDetails> weathers;

    public int getCount() {
        return count;
    }

    public List<WeatherDetails> getWeathers() {
        return weathers;
    }

    @Override
    public String toString() {
        return "WeatherSearchResponse{" +
                "count=" + count +
                ", weathers=" + weathers +
                '}';
    }
}
