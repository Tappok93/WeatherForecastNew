package com.bignerdranch.android.weatherforecast.data.network.requestModel

data class WeatherRequest (
    var city: String,
    val days: Int = 1,
    val aqi: String = "no",
    val alerts: String = "no"
)