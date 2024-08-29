package com.bignerdranch.android.weatherforecast.domain.useCase.useCaseDatabase

import android.content.Context
import androidx.lifecycle.LiveData
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import com.bignerdranch.android.weatherforecast.data.database.BaseCityDao
import com.bignerdranch.android.weatherforecast.data.database.DatabaseCity
import com.bignerdranch.android.weatherforecast.data.repository.repositoryDatabase.DatabaseRepositoryImpl

class GetInfoDatabaseUseCase(context: Context) : DatabaseRepositoryImpl(context) {
    private val baseCityDao: BaseCityDao

    init {
        val database = DatabaseCity.getInstance(context)
        baseCityDao = database!!.getBaseCityDao()
    }

    /**
     * Получение списка объектов из Database
     */
    fun getInfoFromDatabaseUseCase(): LiveData<List<BaseCity>> {
        return baseCityDao.getInfoCity()
    }
}