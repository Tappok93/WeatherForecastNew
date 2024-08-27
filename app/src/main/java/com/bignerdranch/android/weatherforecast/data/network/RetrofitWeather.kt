package com.bignerdranch.android.weatherforecast.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitWeather(private val weatherApi: WeatherApi) {

    fun weatherResultResponse(city: String): LiveData<WeatherResponse> {
        val responseLiveData: MutableLiveData<WeatherResponse> = MutableLiveData()
        Log.d("MyLog", "responseLiveData: ${responseLiveData}")
        val weatherRequest: Call<WeatherResponse> = weatherApi.getWeather(city, "no")
        Log.d("MyLog", "Request: ${weatherRequest}")

        weatherRequest.enqueue(object : Callback<WeatherResponse> {

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("MyLog", "Response fail", t)
            }

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>,
            ) {
                Log.d("MyLog", "Response received")
                val weatherResponseApi: WeatherResponse? = response.body()
                Log.d("MyLog", "weatherResponseApi: ${weatherResponseApi}")


                if (weatherResponseApi != null) {
                    Log.d("MyLog", "Location: ${weatherResponseApi.location.name}")
                    Log.d("MyLog", "Temperature: ${weatherResponseApi.current.temp_c}")
                    Log.d("MyLog", "Date: ${weatherResponseApi.current.last_updated}")
                    Log.d("MyLog", "No response body: ${response.code()}")

                } else {
                    Log.d("MyLog", "No response body")
                }

                responseLiveData.value = weatherResponseApi
            }
        })

        return responseLiveData
    }
}