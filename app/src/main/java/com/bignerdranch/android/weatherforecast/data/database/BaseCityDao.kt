package com.bignerdranch.android.weatherforecast.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BaseCityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInfoCity(city: BaseCity)

//    @Update
//    fun updateInfoCity(city: BaseCity)

    @Query("SELECT * FROM CityList")
    fun getInfoCity(): LiveData<List<BaseCity>>

    @Query("SELECT name FROM CityList WHERE name = :cityName")
    fun getCityName(cityName: String): String?

    @Query("UPDATE CityList SET `temp` = :temp, date = :date WHERE name = :name")
    fun updateCity(name: String, temp: String, date: String)
}