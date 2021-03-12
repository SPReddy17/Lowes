package com.android.lowes.models

import android.os.Parcel
import android.os.Parcelable

class Main : Parcelable {
    constructor() {}

    private var temp: Double? = null
    private var feels_like: Double? = null

    protected constructor(`in`: Parcel) {
        temp = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readDouble()
        }
        feels_like = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readDouble()
        }
    }

    fun getTemp(): Double {
        return Math.round((temp!! - 273.15) * 1.8) + 32.toDouble()
    }

    fun setTemp(temp: Double?) {
        this.temp = temp
    }

    var feelsLike: Double?
        get() = Math.round((feels_like!! - 273.15) * 1.8) + 32.toDouble()
        set(feelsLike) {
            feels_like = feels_like
        }

    override fun toString(): String {
        return "Main{" +
                "temp=" + temp +
                ", feelsLike=" + feels_like +
                '}'
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        if (temp == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeDouble(temp!!)
        }
        if (feels_like == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeDouble(feels_like!!)
        }
    }

    companion object CREATOR : Parcelable.Creator<Main> {
        override fun createFromParcel(parcel: Parcel): Main {
            return Main(parcel)
        }

        override fun newArray(size: Int): Array<Main?> {
            return arrayOfNulls(size)
        }
    }
}