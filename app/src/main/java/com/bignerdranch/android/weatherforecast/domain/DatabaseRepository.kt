package com.bignerdranch.android.weatherforecast.domain

import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    fun insertInfo(city: BaseCity)
    fun getInfo(): Flow<BaseCity>
}