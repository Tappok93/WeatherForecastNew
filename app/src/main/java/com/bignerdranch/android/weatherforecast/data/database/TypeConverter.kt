package com.bignerdranch.android.weatherforecast.data.database

import androidx.room.TypeConverters
import java.util.Date


class TypeConverter {
    @TypeConverters
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverters
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }
}