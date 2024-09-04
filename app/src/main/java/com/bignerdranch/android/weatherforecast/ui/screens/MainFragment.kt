package com.bignerdranch.android.weatherforecast.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bignerdranch.android.weatherforecast.R
import com.bignerdranch.android.weatherforecast.databinding.FragmentFirstBinding
import com.bignerdranch.android.weatherforecast.ui.viewModel.MainFragmentViewModel
import com.bumptech.glide.Glide

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
            mainFragmentViewModel.hideKeyboardFrom(requireContext(), binding.cityEDT)
            mainFragmentViewModel.getWeather(binding.cityEDT.text.toString())

            mainFragmentViewModel.resultResponse.observe(
                viewLifecycleOwner
            ) { weatherData ->
                if (weatherData == null) {
                    Toast.makeText(context, "Не указан город.", Toast.LENGTH_SHORT).show()
                } else {
                    binding.weatherTV.text = weatherData.current.condition.text
                    binding.resultTempFF.text = weatherData.current.temp_c

                    Glide.with(this)
                        .load("https:" + weatherData.current.condition.icon)
                        .into(binding.weatherIV)

                    binding.weatherIV.visibility = VISIBLE
                }
            }
        }

        binding.saveCityBTN.setOnClickListener {
            mainFragmentViewModel.createCityInfoInObject()
            mainFragmentViewModel.saveCityInfoInUi()
            Toast.makeText(context, "Город сохранён в список", Toast.LENGTH_SHORT).show()
        }

        binding.myCityBTN.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_firstFragment_to_secondFragment)
        }

        return binding.root
    }
}





