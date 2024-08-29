package com.bignerdranch.android.weatherforecast.domain.useCase.useCaseNetwork

import androidx.lifecycle.LiveData
import com.bignerdranch.android.weatherforecast.data.network.RetrofitWeather
import com.bignerdranch.android.weatherforecast.data.network.WeatherApi
import com.bignerdranch.android.weatherforecast.data.network.WeatherResponse
import com.bignerdranch.android.weatherforecast.data.repository.repositoryNetwork.NetworkRepository

//TODO Уточнить можно ли реализовать UseCase для синглтона ?
class GetWeatherResultApiUseCase {
    private lateinit var apiWeather: WeatherApi
//    fun getWeatherResultApiUseCase(city: String): LiveData<WeatherResponse> {
//        val retrofitWeather = RetrofitWeather(NetworkRepository.apiWeather)
//        return retrofitWeather.weatherResultResponse(city)
//    }
}