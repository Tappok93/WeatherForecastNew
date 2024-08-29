package com.bignerdranch.android.weatherforecast.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CityList")
class BaseCity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val date: String,
    val temp: String
) {

}