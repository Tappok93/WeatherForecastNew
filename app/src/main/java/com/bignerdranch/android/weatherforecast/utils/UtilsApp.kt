package com.bignerdranch.android.weatherforecast.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class UtilsApp {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatTime(dateTimeString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val dateTime = LocalDateTime.parse(dateTimeString, formatter)
        val outputFormatter = DateTimeFormatter.ofPattern("HH:mm")
        return dateTime.format(outputFormatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun filterDates(dateStrings: List<String>): List<String> {
        val currentTime = LocalTime.now()
        val filteredDates = dateStrings.filter { dateTimeString ->
            val dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            val hour = dateTime.hour
            hour >= currentTime.hour
        }
        return filteredDates
    }
}
