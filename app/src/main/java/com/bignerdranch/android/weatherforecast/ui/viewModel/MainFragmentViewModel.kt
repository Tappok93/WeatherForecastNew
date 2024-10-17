package com.bignerdranch.android.weatherforecast.ui.viewModel


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import com.bignerdranch.android.weatherforecast.data.network.WeatherResponse
import com.bignerdranch.android.weatherforecast.data.network.WeatherResponseDetail
import com.bignerdranch.android.weatherforecast.data.network.requestModel.WeatherRequest
import com.bignerdranch.android.weatherforecast.data.repository.repositoryDatabase.DatabaseRepositoryImpl
import com.bignerdranch.android.weatherforecast.data.repository.repositoryNetwork.NetworkUtils
import com.bignerdranch.android.weatherforecast.domain.useCase.useCaseDatabase.InsertInfoUseCase
import com.bignerdranch.android.weatherforecast.domain.useCase.useCaseNetwork.GetWeatherDetailedHoursUseCase
import com.bignerdranch.android.weatherforecast.domain.useCase.useCaseNetwork.GetWeatherResultApiUseCase
import com.bignerdranch.android.weatherforecast.ui.screens.MyApplication
import com.bignerdranch.android.weatherforecast.utils.UtilsApp


class MainFragmentViewModel : ViewModel() {

    lateinit var resultResponse: LiveData<WeatherResponse>
    lateinit var resultResponceDetail: LiveData<WeatherResponseDetail>
    private lateinit var cityInfo: BaseCity

    @SuppressLint("StaticFieldLeak")
    val networkUtils = NetworkUtils()
    private val repositoryDatabase = DatabaseRepositoryImpl()
    private val insertInfoUseCase = InsertInfoUseCase(repositoryDatabase)
    private val getWeatherResultApiUseCase = GetWeatherResultApiUseCase()
    private val getWeatherDetailedHoursUseCase = GetWeatherDetailedHoursUseCase()

    /**
     * Метод проверки доступа к интернету
     */
    fun internetAccess(context: Context): Boolean {
        return networkUtils.isNetworkAvailable(context)
    }

    /**
     * Метод получения погоды по переданному городу
     */
    fun getWeather(city: String) {
        resultResponse = getWeatherResultApiUseCase.getWeatherResultAPI(city)
    }

    /**
     * Метод получения погоды для детального отображения
     */
    fun getWeatherDetailed(city: WeatherRequest) {
        resultResponceDetail = getWeatherDetailedHoursUseCase.getWeatherDetailed(city)
    }

    /**
     * Метод получения экземпляра класса WeatherRequest
     */
    fun getWeatherRequest(city: String): WeatherRequest {
        return WeatherRequest(city)
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






