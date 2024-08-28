package com.bignerdranch.android.weatherforecast.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bignerdranch.android.weatherforecast.R
import com.bignerdranch.android.weatherforecast.databinding.FragmentFirstBinding
import com.bignerdranch.android.weatherforecast.ui.viewModel.MainFragmentViewModel

const val API_KEY = "0ef8741c77ee4b75b33102700242807"

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var mainFragmentViewModel: MainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        mainFragmentViewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]

        binding.resultWheatherBTN.setOnClickListener {
            mainFragmentViewModel.getWeather(binding.cityEDT.text.toString())
            mainFragmentViewModel.resultResponse.observe(
                viewLifecycleOwner
            ) { weatherData ->
                binding.resultTempFF.text = weatherData.current.temp_c
            }
        }

        binding.saveCityBTN.setOnClickListener {
            mainFragmentViewModel.createCityInfo()
            mainFragmentViewModel.saveCityInfo()
        }

        binding.myCityBTN.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_firstFragment_to_secondFragment)
        }

        return binding.root
    }
}





