package com.bignerdranch.android.weatherforecast.domain

import androidx.lifecycle.LiveData
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    fun insertInfo(city: BaseCity)
    fun getInfo(): LiveData<List<BaseCity>>
}