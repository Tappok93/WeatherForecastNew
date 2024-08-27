package com.bignerdranch.android.weatherforecast.data.network

import com.bignerdranch.android.weatherforecast.ui.screens.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("current.json?key=$API_KEY")
    fun getWeather(
        @Query("q") city: String,
        @Query("aqi") aqi: String
    ): Call<WeatherResponse>
}

