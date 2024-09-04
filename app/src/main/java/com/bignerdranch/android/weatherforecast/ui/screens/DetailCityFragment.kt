package com.bignerdranch.android.weatherforecast.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.weatherforecast.databinding.FragmentThirdBinding
import com.bignerdranch.android.weatherforecast.ui.viewModel.MainFragmentViewModel

class DetailCityFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding
    private lateinit var mainFragmentViewModel: MainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainFragmentViewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        binding = FragmentThirdBinding.inflate(layoutInflater, container, false)

        binding.cityNameTVinDetail.text = arguments?.getString("name")
        binding.resultDateTVinDetail.text = arguments?.getString("data")
        binding.resultTempTVinDetail.text = arguments?.getString("temp")

        binding.resultWeatherBTNinDetail.setOnClickListener {

            mainFragmentViewModel.getWeather(binding.cityNameTVinDetail.text.toString())
            mainFragmentViewModel.resultResponse.observe(
                viewLifecycleOwner
            ) { weatherData ->
                binding.resultTempTVinDetail.text = weatherData.current.temp_c
                binding.resultDateTVinDetail.text = weatherData.current.last_updated
            }

        }

        binding.saveResultBTNinDetail.setOnClickListener {
            mainFragmentViewModel.createCityInfoInObject()
            mainFragmentViewModel.saveCityInfoInUi()
            Toast.makeText(context, "Данные в списке обновлены: Мои города", Toast.LENGTH_SHORT)
                .show()
        }

        return binding.root
    }
}