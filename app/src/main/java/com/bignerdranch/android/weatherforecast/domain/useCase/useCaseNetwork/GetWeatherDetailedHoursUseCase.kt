package com.bignerdranch.android.weatherforecast.domain.useCase.useCaseNetwork

import androidx.lifecycle.LiveData
import com.bignerdranch.android.weatherforecast.data.network.WeatherResponseDetail
import com.bignerdranch.android.weatherforecast.data.network.requestModel.WeatherRequest
import com.bignerdranch.android.weatherforecast.data.repository.repositoryNetwork.NetworkRepository

class GetWeatherDetailedHoursUseCase {

    fun getWeatherDetailed(city: WeatherRequest): LiveData<WeatherResponseDetail> {
        return NetworkRepository.getWeatherWithTime(city)
    }

}