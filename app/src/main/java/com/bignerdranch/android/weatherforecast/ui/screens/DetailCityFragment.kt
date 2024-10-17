package com.bignerdranch.android.weatherforecast.ui.screens

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.weatherforecast.databinding.FragmentThirdBinding
import com.bignerdranch.android.weatherforecast.ui.recyclerView.RecyclerViewAdapterDetailCityFragment
import com.bignerdranch.android.weatherforecast.ui.viewModel.MainFragmentViewModel

class DetailCityFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding
    private lateinit var mainFragmentViewModel: MainFragmentViewModel
    private lateinit var adapter: RecyclerViewAdapterDetailCityFragment
    private lateinit var recycler: RecyclerView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainFragmentViewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        binding = FragmentThirdBinding.inflate(layoutInflater, container, false)

        recycler = binding.thirdFragmentRV
        recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        adapter = RecyclerViewAdapterDetailCityFragment(emptyList())
        recycler.adapter = adapter

        binding.cityNameTVinDetail.text = arguments?.getString("name")
        binding.resultDateTVinDetail.text = arguments?.getString("data")
        binding.resultTempTVinDetail.text = arguments?.getString("temp")

        // Интернет доступен
        if (mainFragmentViewModel.internetAccess(requireContext())) {

            /**
             * Нажатие кнопки [Узнать погоду]
             */
            binding.resultWeatherBTNinDetail.setOnClickListener {

                mainFragmentViewModel.getWeatherDetailed(
                    mainFragmentViewModel.getWeatherRequest(
                        binding.cityNameTVinDetail.text.toString()
                    )
                )
                mainFragmentViewModel.resultResponceDetail.observe(viewLifecycleOwner) { weatherDataDetail ->
                    val hours = weatherDataDetail.forecast.forecastDay.flatMap { it.hour }
                    adapter.updateWeatherList(hours)
                }

            }

            /**
             * Нажатие кнопки [Сохранить новые данные]]
             */
            binding.saveResultBTNinDetail.setOnClickListener {
                mainFragmentViewModel.createCityInfoInObject()
                mainFragmentViewModel.saveCityInfoInUi()
                Toast.makeText(context, "Данные в списке обновлены: Мои города", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {

            // Интернет недоступен, обработка нажатия кнопки [Узнать погоду]
            binding.resultWeatherBTNinDetail.setOnClickListener {
                Toast.makeText(requireContext(), "Интернет недоступен", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }
}