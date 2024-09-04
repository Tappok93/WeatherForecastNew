package com.bignerdranch.android.weatherforecast.domain.useCase.useCaseNetwork

import androidx.lifecycle.LiveData
import com.bignerdranch.android.weatherforecast.data.network.WeatherResponse
import com.bignerdranch.android.weatherforecast.data.repository.repositoryNetwork.NetworkRepository

class GetWeatherResultApiUseCase() {

    fun getWeatherResultAPI(city: String): LiveData<WeatherResponse> {
        return NetworkRepository.getWeatherResultAPI(city)
    }

}