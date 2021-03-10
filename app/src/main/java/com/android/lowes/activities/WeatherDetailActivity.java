package com.android.lowes.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.lowes.R;
import com.android.lowes.models.WeatherDetails;

public class WeatherDetailActivity extends AppCompatActivity {


    // UI components

    private int position;
    private TextView temp, feelsLike,main, description ;

    private static final String TAG = "WeatherDetailActivity";

    private String temperature, feelsTemperature;
    private int tem,feelTem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        temp = findViewById(R.id.temp);
        feelsLike = findViewById(R.id.feels_like);
        main = findViewById(R.id.main);
        description = findViewById(R.id.description);
        Bundle bundle = getIntent().getExtras();
        temp.setText(bundle.getString("temp"));
        feelsLike.setText("feels like : "+bundle.getString("feelsLike"));
        main.setText(bundle.getString("main"));
        description.setText(bundle.getString("description"));
    }


//    private void getIncomingIntent(){
////        if(getIntent().hasExtra("WeatherDetails") && getIntent().hasExtra("position")){
////            WeatherDetails weather ;
////            Bundle bundle = getIntent().getExtras();
////            position = bundle.getInt("position");
////
////            weather = getIntent().getParcelableExtra("WeatherDetails");
////            Log.d(TAG, "getIncomingIntent: " + weather.getWeather().get(position));
////        }
//    }
}
