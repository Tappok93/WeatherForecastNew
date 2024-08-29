package com.bignerdranch.android.weatherforecast.domain.interfaceDatabaseRepositiry

import androidx.lifecycle.LiveData
import com.bignerdranch.android.weatherforecast.data.database.BaseCity

interface DatabaseRepository {
    fun insertOrUpdateInfoDatabase(city: BaseCity)
    fun getInfoFromDatabase(): LiveData<List<BaseCity>>
    fun deleteInfoFromDatabase(name: String)
}