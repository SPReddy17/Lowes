package com.android.lowes.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherDetails implements Parcelable {



    private Main main;

    public WeatherDetails() {
    }

    private List<Weather> weather = null;

    protected WeatherDetails(Parcel in) {
        main = in.readParcelable(Main.class.getClassLoader());
        weather = in.createTypedArrayList(Weather.CREATOR);
    }

    public static final Creator<WeatherDetails> CREATOR = new Creator<WeatherDetails>() {
        @Override
        public WeatherDetails createFromParcel(Parcel in) {
            return new WeatherDetails(in);
        }

        @Override
        public WeatherDetails[] newArray(int size) {
            return new WeatherDetails[size];
        }
    };

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "WeatherDetails{" +
                "main=" + main +
                ", weather=" + weather +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(main, i);
        parcel.writeTypedList(weather);
    }
}