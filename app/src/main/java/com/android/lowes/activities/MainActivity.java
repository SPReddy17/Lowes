package com.android.lowes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.lowes.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button searchButton;
    private EditText cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityName = findViewById(R.id.city_name);

        searchButton  = findViewById(R.id.search_button);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WeatherListActivity.class);
                intent.putExtra("query",cityName.getText().toString());
                startActivity(intent);
            }
        });
    }


}