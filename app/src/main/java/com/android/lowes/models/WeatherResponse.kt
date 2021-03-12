package com.android.lowes.models

import android.os.Parcel
import android.os.Parcelable

class WeatherResponse : Parcelable {
    var cnt: Int? = null
    var list: List<WeatherDetails>? = null

    constructor() {}
    protected constructor(`in`: Parcel) {
        cnt = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readInt()
        }
    }

    override fun toString(): String {
        return "Weather{" +
                "cnt=" + cnt +
                ", list=" + list +
                '}'
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        if (cnt == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeInt(cnt!!)
        }
    }

    companion object CREATOR : Parcelable.Creator<WeatherResponse> {
        override fun createFromParcel(parcel: Parcel): WeatherResponse {
            return WeatherResponse(parcel)
        }

        override fun newArray(size: Int): Array<WeatherResponse?> {
            return arrayOfNulls(size)
        }
    }
}