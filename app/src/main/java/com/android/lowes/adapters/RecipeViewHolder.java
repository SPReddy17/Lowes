package com.android.lowes.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.lowes.R;


public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView weather, temperature;
    OnWeatherListener onWeatherListener;
    public RecipeViewHolder(@NonNull View itemView, OnWeatherListener onWeatherListener) {
        super(itemView);

        this.onWeatherListener = onWeatherListener;

        weather = itemView.findViewById(R.id.weather);
        temperature = itemView.findViewById(R.id.temperature);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onWeatherListener.onWeatherClick(getAdapterPosition());
    }
}
