package com.bignerdranch.android.weatherforecast.useCase

import com.bignerdranch.android.weatherforecast.data.database.BaseCity

interface InsertInfoUseCase {
    suspend fun insertInfo(city: BaseCity)
}

class InsertInfoUseCaseImpl():InsertInfoUseCase {
    override suspend fun insertInfo(city: BaseCity) {
//        Thread {
//            if (baseCityDao.getCityName(city.name) == null) {
//                baseCityDao.insertInfoCity(city)
//            } else {
//                baseCityDao.updateCity(city.name, city.temp, city.date)
//            }
//        }.start()
    }
}

