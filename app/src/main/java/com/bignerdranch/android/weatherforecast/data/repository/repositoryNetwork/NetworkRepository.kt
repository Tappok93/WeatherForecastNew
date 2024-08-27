package com.bignerdranch.android.weatherforecast.data.repository.repositoryNetwork

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.bignerdranch.android.weatherforecast.data.network.RetrofitWeather
import com.bignerdranch.android.weatherforecast.data.network.WeatherApi
import com.bignerdranch.android.weatherforecast.data.network.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkRepository {

    init {
        createRetrofit()
    }

    private lateinit var apiWeather: WeatherApi


    private fun createRetrofit(): WeatherApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiWeather = retrofit.create(WeatherApi::class.java)

        return apiWeather
    }

    fun weatherResultResponse(city: String): LiveData<WeatherResponse> {
        val retrofitWeather = RetrofitWeather(apiWeather)
        return retrofitWeather.weatherResultResponse(city)
    }
}