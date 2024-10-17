package com.bignerdranch.android.weatherforecast.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitWeather {

     fun <T, V> getDataFromServer(input: T, request: (T) -> Call<V>): LiveData<V> {
        val responseLiveData: MutableLiveData<V> = MutableLiveData()

        request(input).enqueue(object : Callback<V> {
            override fun onFailure(call: Call<V>, t: Throwable) {
                Log.e("MyLog", "Response fail", t)
            }

            override fun onResponse(call: Call<V>, response: Response<V>) {
                val responseBody: V? = response.body()
                responseLiveData.value = responseBody
            }
        })

        return responseLiveData
    }
}