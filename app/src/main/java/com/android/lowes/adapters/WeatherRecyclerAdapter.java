package com.android.lowes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.lowes.R;
import com.android.lowes.models.WeatherDetails;

import java.util.List;

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<WeatherDetails> mWeather;
    private OnWeatherListener mOnWeatherListener;

    public WeatherRecyclerAdapter( OnWeatherListener mOnWeatherListener) {

        this.mOnWeatherListener = mOnWeatherListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_weather, viewGroup, false);
        return new RecipeViewHolder(view, mOnWeatherListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
       String weatherType = mWeather.get(i).getWeather().get(0).getMain();
        ((RecipeViewHolder)viewHolder).weather.setText( weatherType);
     //   ((RecipeViewHolder)viewHolder).temperature.setText(Double.toString(Math.round(((mWeather.get(i).getMain().getTemp() - 273.15)*1.8)+32))+" F");
        ((RecipeViewHolder)viewHolder).temperature.setText(Double.toString(mWeather.get(i).getMain().getTemp()));
    }

    @Override
    public int getItemCount() {
        if(mWeather!= null){
            return mWeather.size();
        }else{
            return 0;
        }

    }

    public void setWeather(List<WeatherDetails> weather){
        mWeather = weather;
        notifyDataSetChanged();
    }

    public WeatherDetails getSelectedWeather(int position){
        if(mWeather != null){
            if(mWeather.size() > 0){
                return mWeather.get(position);
            }
        }
        return null;
    }

}