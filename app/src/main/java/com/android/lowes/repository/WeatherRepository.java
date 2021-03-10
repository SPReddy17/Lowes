package com.android.lowes.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.lowes.models.WeatherDetails;
import com.android.lowes.models.WeatherResponse;
import com.android.lowes.requests.WeatherApiClient;

import java.util.List;

public class WeatherRepository {



        private static WeatherRepository instance;
        private WeatherApiClient mWeatherApiCLient;

        public static WeatherRepository getInstance(){
            if(instance == null){
                instance = new WeatherRepository();
            }
            return instance;
        }

        private WeatherRepository() {
            mWeatherApiCLient = WeatherApiClient.getInstance();
        }

//        public LiveData<List<WeatherResponse>> getWeathers(){
//            return mWeatherApiCLient.getWeathers();
//        }

    public LiveData<List<WeatherDetails>> getWeathers(){
            return mWeatherApiCLient.getWeathers();
        }


    public void searchWeathersApi(String query){

        mWeatherApiCLient.searchRecipesApi(query);
    }
    }



