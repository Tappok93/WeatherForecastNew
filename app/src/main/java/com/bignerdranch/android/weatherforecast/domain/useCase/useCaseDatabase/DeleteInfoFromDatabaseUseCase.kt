package com.bignerdranch.android.weatherforecast.domain.useCase.useCaseDatabase

import android.content.Context
import com.bignerdranch.android.weatherforecast.data.database.BaseCityDao
import com.bignerdranch.android.weatherforecast.data.database.DatabaseCity
import com.bignerdranch.android.weatherforecast.data.repository.repositoryDatabase.DatabaseRepositoryImpl

class DeleteInfoFromDatabaseUseCase(context: Context) : DatabaseRepositoryImpl(context) {
    private val baseCityDao: BaseCityDao

    init {
        val database = DatabaseCity.getInstance(context)
        baseCityDao = database!!.getBaseCityDao()
    }

    /**
     * Удаление записи из Database по названию города
     */
    fun deleteInfoFromDatabaseUseCase(name: String) {
        baseCityDao.deleteInfo(name)
    }
}