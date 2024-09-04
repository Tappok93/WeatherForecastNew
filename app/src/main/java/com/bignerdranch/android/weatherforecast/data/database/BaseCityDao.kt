package com.bignerdranch.android.weatherforecast.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BaseCityDao {

    /**
     * Вставка объекта в Database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInfoCity(city: BaseCity)

    /**
     * Получение всего списка объектов из Database
     */
    @Query("SELECT * FROM CityList")
    fun getInfoCity(): LiveData<List<BaseCity>>

    /**
     * Получение записи по названию города из Database
     */
    @Query("SELECT name FROM CityList WHERE name = :cityName")
    fun getCityName(cityName: String): String?

    /**
     * Обновление записи в Database по названию города
     */
    @Query("UPDATE CityList SET `temp` = :temp, date = :date WHERE name = :name")
    fun updateCity(name: String, temp: String, date: String)

    /**
     * Удаление записи в Database
     */
    @Query("DELETE FROM CityList WHERE name = :name")
    fun deleteInfo(name: String)
}