package com.bignerdranch.android.weatherforecast.domain.useCase.useCaseDatabase

import android.content.Context
import com.bignerdranch.android.weatherforecast.data.database.DatabaseCity
import com.bignerdranch.android.weatherforecast.data.repository.repositoryDatabase.DatabaseRepositoryImpl

class DeleteInfoFromDatabaseUseCase(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     * Удаление записи из Database по названию города
     */
    fun deleteInfoFromDatabaseUseCase(name: String) {
        repositoryImpl.deleteInfoFromDatabase(name)
    }
}