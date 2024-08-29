package com.bignerdranch.android.weatherforecast.ui.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import com.bignerdranch.android.weatherforecast.domain.useCase.useCaseDatabase.DeleteInfoFromDatabaseUseCase
import com.bignerdranch.android.weatherforecast.domain.useCase.useCaseDatabase.GetInfoDatabaseUseCase
import com.bignerdranch.android.weatherforecast.ui.screens.MyApplication
import kotlinx.coroutines.launch

class ListCityFragmentViewModel : ViewModel() {
    lateinit var liveDataListBaseCity: LiveData<List<BaseCity>>

    @SuppressLint("StaticFieldLeak")
    val context = MyApplication.getAppContext()
    private val deleteInfoFromDatabaseUseCase = DeleteInfoFromDatabaseUseCase(context)
    private val getInfoDatabaseUseCase = GetInfoDatabaseUseCase(context)

    /**
     * Метод получения LiveData с объектами BaseCity
     */
    fun getInfoCityFromDatabase(): LiveData<List<BaseCity>> {
        liveDataListBaseCity = getInfoDatabaseUseCase.getInfoFromDatabaseUseCase()
        return liveDataListBaseCity
    }

    /**
     * Метод удаления объекта из BaseCity
     */
    fun deleteCityForList(name: String) {
        Thread {
            deleteInfoFromDatabaseUseCase.deleteInfoFromDatabaseUseCase(name)
        }.start()
    }
}
