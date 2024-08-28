package com.bignerdranch.android.weatherforecast.data.repository.repositoryDatabase

import android.content.Context
import androidx.lifecycle.LiveData
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import com.bignerdranch.android.weatherforecast.data.database.BaseCityDao
import com.bignerdranch.android.weatherforecast.data.database.DatabaseCity
import com.bignerdranch.android.weatherforecast.domain.DatabaseRepository
import com.bignerdranch.android.weatherforecast.ui.screens.MyApplication
import kotlinx.coroutines.flow.Flow


class DatabaseRepositoryImpl(context: Context) : DatabaseRepository {

    private val baseCityDao: BaseCityDao
    val context = MyApplication.getAppContext()

    init {
        val database = DatabaseCity.getInstance(context)
        baseCityDao = database!!.getBaseCityDao()
    }

    override fun insertInfo(city: BaseCity) {

        Thread {
            if (baseCityDao.getCityName(city.name) == null) {
                baseCityDao.insertInfoCity(city)
            } else {
                //Toast.makeText(context, "Город ранее был сохранен в списке, данные по погоде обновлены.", Toast.LENGTH_SHORT).show()
                baseCityDao.updateCity(city.name, city.temp, city.date)
            }
        }.start()
    }

    override fun getInfo(): LiveData<List<BaseCity>> {
        return baseCityDao.getInfoCity()
    }
}