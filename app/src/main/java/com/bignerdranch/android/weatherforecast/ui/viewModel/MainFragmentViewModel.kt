package com.bignerdranch.android.weatherforecast.ui.viewModel


import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import com.bignerdranch.android.weatherforecast.data.network.WeatherResponse
import com.bignerdranch.android.weatherforecast.data.repository.repositoryDatabase.DatabaseRepositoryImpl
import com.bignerdranch.android.weatherforecast.data.repository.repositoryNetwork.NetworkRepository
import com.bignerdranch.android.weatherforecast.ui.screens.MyApplication


class MainFragmentViewModel() : ViewModel() {

    lateinit var resultResponse: LiveData<WeatherResponse>
    private lateinit var cityInfo: BaseCity
    private var databaseRepositoryImpl: DatabaseRepositoryImpl
    @SuppressLint("StaticFieldLeak")
    val context = MyApplication.getAppContext()

    init {
        databaseRepositoryImpl = DatabaseRepositoryImpl(context)
    }

    fun getWeather(city: String) {
        resultResponse = NetworkRepository.weatherResultResponse(city)
    }

    fun createCityInfo(): BaseCity {
        cityInfo = BaseCity(
            name = resultResponse.value?.location?.name ?: "Name not found",
            date = resultResponse.value!!.current.last_updated,
            temp = resultResponse.value!!.current.temp_c
        )
        return cityInfo
    }

    fun saveCityInfo() {
        databaseRepositoryImpl.insertInfo(cityInfo)
    }
}






