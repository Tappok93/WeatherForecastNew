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
        val weatherRequest: Call<WeatherResponse> = weatherApi.getWeather(city, "no")

        /**
         * Создание запроса на сайт и обработка ответа
         */
        weatherRequest.enqueue(object : Callback<WeatherResponse> {

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("MyLog", "Response fail", t)
            }

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>,
            ) {
                val weatherResponseApi: WeatherResponse? = response.body()
                responseLiveData.value = weatherResponseApi
            }
        })

        return responseLiveData
    }
}