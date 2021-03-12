package com.android.lowes.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.android.lowes.R
import kotlin.jvm.java as java

class MainActivity : AppCompatActivity() {
    private var searchButton: Button? = null
    private var cityName: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cityName = findViewById(R.id.city_name)
        searchButton = findViewById(R.id.search_button)
        searchButton?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, WeatherListActivity::class.java)
            intent.putExtra("query", cityName?.text.toString())
            startActivity(intent)
        })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}