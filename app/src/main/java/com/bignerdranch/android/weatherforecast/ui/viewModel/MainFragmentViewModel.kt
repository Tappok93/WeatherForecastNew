package com.bignerdranch.android.weatherforecast.ui.viewModel


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import com.bignerdranch.android.weatherforecast.data.network.WeatherResponse
import com.bignerdranch.android.weatherforecast.data.repository.repositoryDatabase.DatabaseRepositoryImpl
import com.bignerdranch.android.weatherforecast.data.repository.repositoryNetwork.NetworkRepository
import com.bignerdranch.android.weatherforecast.domain.useCase.useCaseDatabase.InsertInfoUseCase
import com.bignerdranch.android.weatherforecast.domain.useCase.useCaseNetwork.GetWeatherResultApiUseCase
import com.bignerdranch.android.weatherforecast.ui.screens.MyApplication


class MainFragmentViewModel() : ViewModel() {

    lateinit var resultResponse: LiveData<WeatherResponse>
    private lateinit var cityInfo: BaseCity

    @SuppressLint("StaticFieldLeak")
    val context = MyApplication.getAppContext()
    private val repositoryDatabase = DatabaseRepositoryImpl(context)
    private val insertInfoUseCase = InsertInfoUseCase(repositoryDatabase)
    private val getWeatherResultApiUseCase = GetWeatherResultApiUseCase()

    /**
     * Метод получения погоды по переданному городу
     */
    fun getWeather(city: String) {
        resultResponse = getWeatherResultApiUseCase.getWeatherResultAPI(city)
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

    /**
     * Метод скрытия системной клавиатуры
     */
    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}






