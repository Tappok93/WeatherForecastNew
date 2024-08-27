package com.bignerdranch.android.weatherforecast.data.database

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

    @Update
    fun updateInfoCity(city: BaseCity)

    @Query("SELECT * FROM citylist")
    fun getInfoCity(): Flow<BaseCity>

    @Query("SELECT id, name FROM CityList WHERE name = :cityName")
    fun getCityName(cityName: String): String?

    @Query("UPDATE CityList SET name = :name WHERE id = :id")
    suspend fun updateItemName(id: Int, name: String)
}