package com.android.lowes.requests

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.lowes.AppExecutors
import com.android.lowes.models.WeatherDetails
import com.android.lowes.requests.responses.WeatherSearchResponse
import com.android.lowes.util.Constants
import retrofit2.Call
import retrofit2.Response
import java.util.*
import java.util.concurrent.TimeUnit

class WeatherApiClient private constructor() {
    private  val TAG = "WeatherApiClient"
    private val mWeathers: MutableLiveData<List<WeatherDetails>?>
    private var mRetreiveWeatherRunnable: RetrieveWeatherRunnable? = null
    val weathers: LiveData<List<WeatherDetails>?>
        get() = mWeathers

    fun searchRecipesApi(query: String) {
        if (mRetreiveWeatherRunnable != null) {
            mRetreiveWeatherRunnable = null
        }
        mRetreiveWeatherRunnable = RetrieveWeatherRunnable(query)
        val handler = AppExecutors.get()?.networkIO()?.submit(mRetreiveWeatherRunnable)

        // Set a timeout for the data refresh
        AppExecutors.get()?.networkIO()?.schedule({ // let the user know it timed out
            handler?.cancel(true)
        }, Constants.NETWORK_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
    }

    private inner class RetrieveWeatherRunnable public constructor(private val query: String) : Runnable {
        private var cancelRequest = false
        override fun run() {
            try {
                val response: Response<*> = getWeathers(query)!!.execute()
                if (cancelRequest) {
                    return
                }
                if (response.code() == 200) {
                    val list: List<WeatherDetails> = ArrayList((response.body() as WeatherSearchResponse?)!!.weathers!!)
                    mWeathers.postValue(list)
                } else {
                    val error = response.errorBody()!!.string()
                    Log.e(TAG, "run: error: $error")
                    mWeathers.postValue(null)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                mWeathers.postValue(null)
            }
        }

        private fun getWeathers(query: String): Call<WeatherSearchResponse?>? {
            return ServiceGenerator.weatherApi.searchWeather(
                    query,
                    Constants.API_KEY
            )
        }

        private fun cancelRequest() {
            Log.d(TAG, "cancelRequest: canceling the retrieval query")
            cancelRequest = true
        }

    }


    init {
        mWeathers = MutableLiveData()
    }

    private object HOLDER {
        val INSTANCE = WeatherApiClient()
    }

    companion object {
        val instance: WeatherApiClient by lazy { HOLDER.INSTANCE }
    }
}