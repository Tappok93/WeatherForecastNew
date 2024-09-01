package com.bignerdranch.android.weatherforecast.domain.useCase.useCaseDatabase

import android.content.Context
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import com.bignerdranch.android.weatherforecast.data.database.BaseCityDao
import com.bignerdranch.android.weatherforecast.data.database.DatabaseCity
import com.bignerdranch.android.weatherforecast.data.repository.repositoryDatabase.DatabaseRepositoryImpl

class InsertInfoUseCase(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     * Метод сохранения или обновления данных в Database
     */
    fun insertOrUpdateInfoDatabaseUseCase(city: BaseCity) {
        repositoryImpl.insertOrUpdateInfoDatabase(city)
    }
}

