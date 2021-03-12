package com.android.lowes.models

import android.os.Parcel
import android.os.Parcelable

class Weather : Parcelable {
    constructor() {}

    var main: String? = null
    var description: String? = null

    protected constructor(`in`: Parcel) {
        main = `in`.readString()
        description = `in`.readString()
    }

    override fun toString(): String {
        return "Weather{" +
                "main='" + main + '\'' +
                ", description='" + description + '\'' +
                '}'
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(main)
        parcel.writeString(description)
    }

    companion object CREATOR : Parcelable.Creator<Weather> {
        override fun createFromParcel(parcel: Parcel): Weather {
            return Weather(parcel)
        }

        override fun newArray(size: Int): Array<Weather?> {
            return arrayOfNulls(size)
        }
    }
}