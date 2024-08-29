package com.bignerdranch.android.weatherforecast.domain.useCase.useCaseDatabase

import android.content.Context
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import com.bignerdranch.android.weatherforecast.data.database.BaseCityDao
import com.bignerdranch.android.weatherforecast.data.database.DatabaseCity
import com.bignerdranch.android.weatherforecast.data.repository.repositoryDatabase.DatabaseRepositoryImpl

class InsertInfoUseCase(context: Context) : DatabaseRepositoryImpl(context) {
    private val baseCityDao: BaseCityDao

    init {
        val database = DatabaseCity.getInstance(context)
        baseCityDao = database!!.getBaseCityDao()
    }

    fun insertOrUpdateInfoDatabaseUseCase(city: BaseCity) {
        Thread {
            if (baseCityDao.getCityName(city.name) == null) {
                baseCityDao.insertInfoCity(city)
            } else {
                baseCityDao.updateCity(city.name, city.temp, city.date)
            }
        }.start()
    }
}

