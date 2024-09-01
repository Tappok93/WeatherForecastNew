package com.bignerdranch.android.weatherforecast.domain.useCase.useCaseDatabase

import android.content.Context
import androidx.lifecycle.LiveData
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import com.bignerdranch.android.weatherforecast.data.repository.repositoryDatabase.DatabaseRepositoryImpl

class GetInfoDatabaseUseCase(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     * Получение списка объектов из Database
     */
    fun getInfoFromDatabaseUseCase(): LiveData<List<BaseCity>> {
        return repositoryImpl.getInfoFromDatabase()
    }
}