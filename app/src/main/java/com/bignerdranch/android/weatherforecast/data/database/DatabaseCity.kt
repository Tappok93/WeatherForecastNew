package com.bignerdranch.android.weatherforecast.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BaseCity::class], version = 1)
abstract class DatabaseCity : RoomDatabase() {
    abstract fun getBaseCityDao(): BaseCityDao

    companion object {

        private var INSTANCE: DatabaseCity? = null

        fun getInstance(context: Context): DatabaseCity? {
            if (INSTANCE == null) {
                synchronized(DatabaseCity::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        DatabaseCity::class.java,
                        "database_city"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}