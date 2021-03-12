package com.android.lowes.models

import android.os.Parcel
import android.os.Parcelable

class WeatherDetails : Parcelable {
    var main: Main? = null

    constructor() {}

    var weather: List<Weather>? = null

    protected constructor(`in`: Parcel) {
        main = `in`.readParcelable(Main::class.java.classLoader)
        weather = `in`.createTypedArrayList(Weather.CREATOR)
    }

    override fun toString(): String {
        return "WeatherDetails{" +
                "main=" + main +
                ", weather=" + weather +
                '}'
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeParcelable(main, i)
        parcel.writeTypedList(weather)
    }


    companion object CREATOR : Parcelable.Creator<WeatherDetails> {
        override fun createFromParcel(parcel: Parcel): WeatherDetails {
            return WeatherDetails(parcel)
        }

        override fun newArray(size: Int): Array<WeatherDetails?> {
            return arrayOfNulls(size)
        }
    }
}