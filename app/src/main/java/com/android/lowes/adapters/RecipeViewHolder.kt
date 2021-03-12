package com.android.lowes.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.lowes.R

class RecipeViewHolder(itemView: View, var onWeatherListener: OnWeatherListener) : ViewHolder(itemView), View.OnClickListener {

    var weather: TextView

    var temperature: TextView
    override fun onClick(v: View) {
        onWeatherListener.onWeatherClick(adapterPosition)
    }

    init {
        weather = itemView.findViewById(R.id.weather)
        temperature = itemView.findViewById(R.id.temperature)
        itemView.setOnClickListener(this)
    }
}