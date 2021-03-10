package com.android.lowes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.lowes.R;
import com.android.lowes.adapters.OnWeatherListener;
import com.android.lowes.adapters.WeatherRecyclerAdapter;
import com.android.lowes.models.WeatherDetails;
import com.android.lowes.models.WeatherResponse;
import com.android.lowes.requests.ServiceGenerator;
import com.android.lowes.requests.WeatherApi;
import com.android.lowes.requests.responses.WeatherSearchResponse;
import com.android.lowes.util.Constants;
import com.android.lowes.viewmodels.WeatherListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherListActivity extends AppCompatActivity  implements OnWeatherListener {

    private WeatherListViewModel mWeatherListViewModel;

    private static final String TAG = "WeatherListActivity";

    private RecyclerView mRecyclerView;
    private WeatherRecyclerAdapter mAdapter;
    private String weatherQuery;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
        mWeatherListViewModel = ViewModelProviders.of(this).get(WeatherListViewModel.class);

        Bundle bundle = getIntent().getExtras();
        weatherQuery = bundle.getString("query");
        mRecyclerView = findViewById(R.id.weather_list);
        testRetrofitRequest();
        initRecyclerView();
        subscribeObservers();
    }
    private void subscribeObservers(){

//       mWeatherListViewModel.getWeathers().observe(this, new Observer<List<WeatherResponse>>() {
//           @Override
//           public void onChanged(List<WeatherResponse> weatherResponses) {
//
//           }
//       });

        mWeatherListViewModel.getWeathers().observe(this, new Observer<List<WeatherDetails>>() {
            @Override
            public void onChanged(List<WeatherDetails> weatherDetails) {
                if(weatherDetails != null){
                    for(WeatherDetails weather : weatherDetails){
                        Log.d(TAG, "onChanged: "+weather.getWeather());
                        Log.d(TAG, "onChanged: " +weather.getMain());
                    }
                    mAdapter.setWeather(weatherDetails);
                }

            }
        });
    }


    private void initRecyclerView(){
        mAdapter = new WeatherRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void searchWeathersApi(String query){
        mWeatherListViewModel.searchWeathersApi(query);
    }

    private void testRetrofitRequest() {

       searchWeathersApi(weatherQuery);
//        WeatherApi weatherApi = ServiceGenerator.getWeatherApi();
//        // Do search using Retrofit
//        Call<WeatherSearchResponse> responseCall = weatherApi
//                .searchWeather(
//                        "naruto",
//                        Constants.API_KEY
//                );
//
//        responseCall.enqueue(new Callback<WeatherSearchResponse>() {
//            @Override
//            public void onResponse(Call<WeatherSearchResponse> call, Response<WeatherSearchResponse> response) {
//                Log.d(TAG, "onResponse: Server Response: " + response.toString());
//                if(response.code() == 200){
//                    Log.d(TAG, "onResponse: " + response.body().toString());
//                    List<WeatherDetails> weather = new ArrayList<>(response.body().getWeathers());
//                    for(WeatherDetails weth: weather){
//                        Log.d(TAG, "onResponse: " + weth.toString());
//                    }
//                }
//                else {
//                    try {
//                        Log.d(TAG, "onResponse: " + response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<WeatherSearchResponse> call, Throwable t) {
//
//            }
//        });
//    }

       }

    @Override
    public void onWeatherClick(int position) {
        Intent intent = new Intent(this, WeatherDetailActivity.class);
        intent.putExtra("temp", mAdapter.getSelectedWeather(position).getMain().getTemp().toString());
        intent.putExtra("feelsLike", mAdapter.getSelectedWeather(position).getMain().getFeelsLike().toString());
        intent.putExtra("main",mAdapter.getSelectedWeather(position).getWeather().get(0).getMain());
        intent.putExtra("description",mAdapter.getSelectedWeather(position).getWeather().get(0).getDescription());
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String category) {

    }
}
