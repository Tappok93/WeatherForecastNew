package com.bignerdranch.android.weatherforecast.data.repository.repositoryNetwork

import androidx.lifecycle.LiveData
import com.bignerdranch.android.weatherforecast.data.network.RetrofitWeather
import com.bignerdranch.android.weatherforecast.data.network.WeatherApi
import com.bignerdranch.android.weatherforecast.data.network.WeatherResponse
import com.bignerdranch.android.weatherforecast.data.network.WeatherResponseDetail
import com.bignerdranch.android.weatherforecast.data.network.requestModel.WeatherRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkRepository {

    init {
        createRetrofit()
    }

    private lateinit var apiWeather: WeatherApi
    private var retrofitWeather = RetrofitWeather()

    /**
     * Метод создания Retrofit для запроса
     */
    private fun createRetrofit(): WeatherApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiWeather = retrofit.create(WeatherApi::class.java)

        return apiWeather
    }

    /**
     * Метод получения погоды по переданному городу
     */
    fun getWeatherWithCountry(city: String): LiveData<WeatherResponse> {
        return retrofitWeather.getDataFromServer(city) { apiWeather.getWeatherRealTime(city, "no") }
    }

    /**
     * Метод получения погоды для детального отображения
     */
    fun getWeatherWithTime(request: WeatherRequest): LiveData<WeatherResponseDetail> {
        return retrofitWeather.getDataFromServer(request.city) { apiWeather.getWeatherHours(
            request.city,
            request.days,
            request.aqi,
            request.alerts
        ) }
    }
}