package com.android.lowes.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.lowes.R

class WeatherDetailActivity : AppCompatActivity() {
    // UI components
    private val position = 0
    private var temp: TextView? = null
    private var feelsLike: TextView? = null
    private var main: TextView? = null
    private var description: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)
        temp = findViewById(R.id.temp)
        feelsLike = findViewById(R.id.feels_like)
        main = findViewById(R.id.main)
        description = findViewById(R.id.description)
        val bundle = intent.extras
        temp?.text = (bundle!!.getString("temp"))
        feelsLike?.text = ("feels like : " + bundle.getString("feelsLike"))
        main?.text = (bundle.getString("main"))
        description?.text = (bundle.getString("description"))
    }

    companion object {
        private const val TAG = "WeatherDetailActivity"
    }
}