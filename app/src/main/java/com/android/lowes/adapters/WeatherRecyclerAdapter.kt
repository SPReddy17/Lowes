package com.android.lowes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.lowes.R
import com.android.lowes.models.WeatherDetails

class WeatherRecyclerAdapter(private val mOnWeatherListener: OnWeatherListener) : RecyclerView.Adapter<ViewHolder>() {
    private var mWeather: List<WeatherDetails>? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_weather, viewGroup, false)
        return RecipeViewHolder(view, mOnWeatherListener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val weatherType = mWeather!![i].weather?.get(0)?.main
        (viewHolder as RecipeViewHolder).weather.text = weatherType
        //   ((RecipeViewHolder)viewHolder).temperature.setText(Double.toString(Math.round(((mWeather.get(i).getMain().getTemp() - 273.15)*1.8)+32))+" F");
        viewHolder.temperature.text = mWeather!![i].main?.getTemp()?.let { java.lang.Double.toString(it) }
    }

    override fun getItemCount(): Int {
        return if (mWeather != null) {
            mWeather!!.size
        } else {
            0
        }
    }

    fun setWeather(weather: List<WeatherDetails>?) {
        mWeather = weather
        notifyDataSetChanged()
    }

    fun getSelectedWeather(position: Int): WeatherDetails? {
        if (mWeather != null) {
            if (mWeather!!.size > 0) {
                return mWeather!![position]
            }
        }
        return null
    }

}