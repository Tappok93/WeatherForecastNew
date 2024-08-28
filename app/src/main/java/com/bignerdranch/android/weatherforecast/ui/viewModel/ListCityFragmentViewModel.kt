package com.bignerdranch.android.weatherforecast.ui.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import com.bignerdranch.android.weatherforecast.data.repository.repositoryDatabase.DatabaseRepositoryImpl
import com.bignerdranch.android.weatherforecast.ui.screens.MyApplication

class ListCityFragmentViewModel: ViewModel(){

    private var databaseRepositoryImpl: DatabaseRepositoryImpl
    lateinit var resultResponse: LiveData<List<BaseCity>>

    @SuppressLint("StaticFieldLeak")
    val context = MyApplication.getAppContext()

    init {
        databaseRepositoryImpl = DatabaseRepositoryImpl(context)
    }


    fun getCityInfo(): LiveData<List<BaseCity>>{
        resultResponse = databaseRepositoryImpl.getInfo()
        return resultResponse
    }
}