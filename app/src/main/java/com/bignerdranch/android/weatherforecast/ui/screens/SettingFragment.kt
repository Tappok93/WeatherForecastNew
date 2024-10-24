package com.bignerdranch.android.weatherforecast.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bignerdranch.android.weatherforecast.R
import com.bignerdranch.android.weatherforecast.databinding.SettingMenuBinding

class SettingFragment : Fragment() {
    private lateinit var binding: SettingMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingMenuBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        return binding.root
    }

}