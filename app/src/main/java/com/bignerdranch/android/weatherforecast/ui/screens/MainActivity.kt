package com.bignerdranch.android.weatherforecast.ui.screens

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bignerdranch.android.weatherforecast.R
import com.bignerdranch.android.weatherforecast.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //MAIN = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}