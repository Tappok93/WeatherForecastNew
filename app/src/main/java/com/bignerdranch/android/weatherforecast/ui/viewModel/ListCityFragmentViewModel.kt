package com.bignerdranch.android.weatherforecast.ui.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import com.bignerdranch.android.weatherforecast.data.repository.repositoryDatabase.DatabaseRepositoryImpl
import com.bignerdranch.android.weatherforecast.domain.useCase.useCaseDatabase.DeleteInfoFromDatabaseUseCase
import com.bignerdranch.android.weatherforecast.domain.useCase.useCaseDatabase.GetInfoDatabaseUseCase
import com.bignerdranch.android.weatherforecast.ui.screens.MyApplication

class ListCityFragmentViewModel : ViewModel() {
    lateinit var liveDataListBaseCity: LiveData<List<BaseCity>>

    @SuppressLint("StaticFieldLeak")
    val context = MyApplication.getAppContext()
    private val repositoryDatabase = DatabaseRepositoryImpl(context)
    private val deleteInfoFromDatabaseUseCase = DeleteInfoFromDatabaseUseCase(repositoryDatabase)
    private val getInfoDatabaseUseCase = GetInfoDatabaseUseCase(repositoryDatabase)

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
        deleteInfoFromDatabaseUseCase.deleteInfoFromDatabaseUseCase(name)
    }
}
