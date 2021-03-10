package com.android.lowes.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.lowes.AppExecutors;
import com.android.lowes.models.WeatherDetails;
import com.android.lowes.models.WeatherResponse;
import com.android.lowes.requests.responses.WeatherSearchResponse;
import com.android.lowes.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.android.lowes.util.Constants.NETWORK_TIMEOUT;

public class WeatherApiClient {
    private static final String TAG = "WeatherApiClient";
    private static WeatherApiClient instance;


//    private MutableLiveData<List<WeatherResponse>> mWeathers;
private MutableLiveData<List<WeatherDetails>> mWeathers;
    private RetrieveWeatherRunnable mRetreiveWeatherRunnable;


    public static WeatherApiClient getInstance(){
        if(instance == null){
            instance = new WeatherApiClient();
        }
        return instance;
    }

    private WeatherApiClient() {
        mWeathers = new MutableLiveData<>();
    }

//    public LiveData<List<WeatherResponse>> getWeathers(){
//        return mWeathers;
//    }
    public LiveData<List<WeatherDetails>> getWeathers(){
        return mWeathers;
    }

    public void searchRecipesApi(String query){
        if(mRetreiveWeatherRunnable != null){
            mRetreiveWeatherRunnable = null;
        }
        mRetreiveWeatherRunnable = new RetrieveWeatherRunnable(query);
        final Future handler = AppExecutors.get().networkIO().submit(mRetreiveWeatherRunnable);

        // Set a timeout for the data refresh
        AppExecutors.get().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // let the user know it timed out
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveWeatherRunnable implements Runnable{

        private String query;

        private boolean cancelRequest;

        private RetrieveWeatherRunnable(String query) {
            this.query = query;
            cancelRequest = false;
        }

        @Override
        public void run() {

            try {
                Response response = getWeathers(query).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){
                    List<WeatherDetails> list = new ArrayList<>(((WeatherSearchResponse)response.body()).getWeathers());

                      //  List<WeatherDetails> currentWeather = mWeathers.getValue();

                     //   currentWeather.addAll(list);
                        mWeathers.postValue(list);

                }
                else{
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: error: " + error);
                    mWeathers.postValue(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                mWeathers.postValue(null);
            }
        }

        private Call<WeatherSearchResponse> getWeathers(String query){
            return ServiceGenerator.getWeatherApi().searchWeather(
                    query,
                    Constants.API_KEY
            );
        }

        private void cancelRequest(){
            Log.d(TAG, "cancelRequest: canceling the retrieval query");
            cancelRequest = true;
        }
    }
}