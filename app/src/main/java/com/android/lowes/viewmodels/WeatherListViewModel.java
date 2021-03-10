package com.android.lowes.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.lowes.models.WeatherDetails;
import com.android.lowes.models.WeatherResponse;
import com.android.lowes.repository.WeatherRepository;

import java.util.List;

public class WeatherListViewModel extends ViewModel {

    private WeatherRepository mWeatherRepository;

    public WeatherListViewModel() {
        mWeatherRepository = WeatherRepository.getInstance();
    }

//    public LiveData<List<WeatherResponse>> getWeathers() {
//        return mWeatherRepository.getWeathers();
//    }
public LiveData<List<WeatherDetails>> getWeathers() {
    return mWeatherRepository.getWeathers();
}
    public void searchWeathersApi(String query){
        mWeatherRepository.searchWeathersApi(query);
    }
}