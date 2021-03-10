package com.android.lowes.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main  implements Parcelable {

    public Main() {
    }

    private Double temp;
    private Double feels_like;

    protected Main(Parcel in) {
        if (in.readByte() == 0) {
            temp = null;
        } else {
            temp = in.readDouble();
        }
        if (in.readByte() == 0) {
            feels_like = null;
        } else {
            feels_like = in.readDouble();
        }
    }

    public static final Creator<Main> CREATOR = new Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel in) {
            return new Main(in);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };

    public Double getTemp() {

        double d =  Math.round((temp-273.15)*1.8)+32;
        return d;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getFeelsLike() {
        double f =  Math.round((feels_like-273.15)*1.8)+32;
        return f;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feels_like = feels_like;
    }

    @Override
    public String toString() {
        return "Main{" +
                "temp=" + temp +
                ", feelsLike=" + feels_like +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (temp == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(temp);
        }
        if (feels_like == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(feels_like);
        }
    }
}