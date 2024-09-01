package com.bignerdranch.android.weatherforecast.data.repository.repositoryDatabase

import android.content.Context
import androidx.lifecycle.LiveData
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import com.bignerdranch.android.weatherforecast.data.database.BaseCityDao
import com.bignerdranch.android.weatherforecast.data.database.DatabaseCity
import com.bignerdranch.android.weatherforecast.domain.interfaceDatabaseRepositiry.DatabaseRepository
import com.bignerdranch.android.weatherforecast.ui.screens.MyApplication


open class DatabaseRepositoryImpl(context: Context) : DatabaseRepository {
    private val baseCityDao: BaseCityDao
    val context = MyApplication.getAppContext()

    init {
        val database = DatabaseCity.getInstance(context)
        baseCityDao = database!!.getBaseCityDao()
    }

    /**
     * Реализация интерфеса по вставке или обновлении данных в Database
     */
    override fun insertOrUpdateInfoDatabase(city: BaseCity) {

        Thread {
            if (baseCityDao.getCityName(city.name) == null) {
                baseCityDao.insertInfoCity(city)
            } else {
                baseCityDao.updateCity(city.name, city.temp, city.date)
            }
        }.start()
    }

    /**
     * Реализация интерфеса по получению списка объектов из Database
     */
    override fun getInfoFromDatabase(): LiveData<List<BaseCity>> {
        return baseCityDao.getInfoCity()
    }

    /**
     * Реализация интерфеса по удалению объекта из Database
     */
    override fun deleteInfoFromDatabase(name: String) {
            baseCityDao.deleteInfo(name)
    }
}