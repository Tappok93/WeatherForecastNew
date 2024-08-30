package com.bignerdranch.android.weatherforecast.ui.viewModel


import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import com.bignerdranch.android.weatherforecast.data.network.WeatherResponse
import com.bignerdranch.android.weatherforecast.data.repository.repositoryNetwork.NetworkRepository
import com.bignerdranch.android.weatherforecast.domain.useCase.useCaseDatabase.InsertInfoUseCase
import com.bignerdranch.android.weatherforecast.ui.screens.MyApplication


class MainFragmentViewModel() : ViewModel() {

    lateinit var resultResponse: LiveData<WeatherResponse>
    private lateinit var cityInfo: BaseCity

    @SuppressLint("StaticFieldLeak")
    val context = MyApplication.getAppContext()
    private val insertInfoUseCase = InsertInfoUseCase(context)


    /**
     * Метод получения погоды по переданному городу
     */
    fun getWeather(city: String) {
        resultResponse = NetworkRepository.getWeatherResultAPI(city)
    }

    /**
     * Метод создания объекта BaseCity с данными
     */
    fun createCityInfoInObject(): BaseCity {
        cityInfo = BaseCity(
            name = resultResponse.value?.location?.name ?: "Name not found",
            date = resultResponse.value!!.current.last_updated,
            temp = resultResponse.value!!.current.temp_c,
        )
        return cityInfo
    }

    /**
     * Метод сохранения или обновления данных по городу в Database
     */
    fun saveCityInfoInUi() {
        insertInfoUseCase.insertOrUpdateInfoDatabaseUseCase(cityInfo)
    }
}






