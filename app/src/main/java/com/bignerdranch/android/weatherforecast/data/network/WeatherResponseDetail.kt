package com.bignerdranch.android.weatherforecast.data.network

import com.google.gson.annotations.SerializedName

data class WeatherResponseDetail (
    val forecast: Forecast,
)

data class Forecast (
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDay>
)

data class ForecastDay(
    val hour: List<Hour>
)

data class Hour (
    val temp_c: String,
    val time: String,
    val condition: Condition
)
