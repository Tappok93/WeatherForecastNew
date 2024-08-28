package com.bignerdranch.android.weatherforecast.domain

import androidx.lifecycle.LiveData
import com.bignerdranch.android.weatherforecast.data.database.BaseCity

interface DatabaseRepository {
    fun insertInfo(city: BaseCity)
    fun getInfo(): LiveData<List<BaseCity>>
}