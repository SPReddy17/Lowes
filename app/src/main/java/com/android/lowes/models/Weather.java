package com.android.lowes.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather  implements Parcelable {
    public Weather() {
    }

    private String main;

    private String description;

    protected Weather(Parcel in) {
        main = in.readString();
        description = in.readString();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    //    @Override
//    public String toString() {
//        return "Weather{" +
//                "main='" + main + '\'' +
//                ", description='" + description + '\'' +
//                '}';
//    }
    @Override
    public String toString() {
        return main;
    }


    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(main);
        parcel.writeString(description);
    }
}