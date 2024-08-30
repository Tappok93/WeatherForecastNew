package com.bignerdranch.android.weatherforecast.data.network


data class WeatherResponse(
    val location: Location,
    val current: Current,
)

data class Location(
    val name: String
)

data class Current(
    val temp_c: String,
    val last_updated: String,
    val condition: Condition
)

data class Condition(
    val text: String,
    val icon: String
)








