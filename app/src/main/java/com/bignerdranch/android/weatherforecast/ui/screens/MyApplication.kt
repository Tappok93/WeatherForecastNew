package com.bignerdranch.android.weatherforecast.ui.screens

import android.app.Application
import android.content.Context


class MyApplication : Application() {
    companion object {
        private lateinit var instance: MyApplication

        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
