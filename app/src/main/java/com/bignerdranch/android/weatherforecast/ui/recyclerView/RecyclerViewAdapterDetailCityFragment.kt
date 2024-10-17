package com.bignerdranch.android.weatherforecast.ui.recyclerView

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.weatherforecast.R
import com.bignerdranch.android.weatherforecast.data.network.Hour
import com.bignerdranch.android.weatherforecast.databinding.ScreenBoxElementFragmentThirdBinding
import com.bignerdranch.android.weatherforecast.utils.UtilsApp
import com.bumptech.glide.Glide

class RecyclerViewAdapterDetailCityFragment(private var hourList: List<Hour>) :
    RecyclerView.Adapter<RecyclerViewAdapterDetailCityFragment.ViewHolder>() {
        val utilsApp = UtilsApp()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ScreenBoxElementFragmentThirdBinding.bind(itemView)


        @RequiresApi(Build.VERSION_CODES.O)
        fun setData(hour: Hour) {
            val formattedTime = utilsApp.formatTime(hour.time)
            binding.timeRC.text = formattedTime
            binding.tempRC.text = hour.temp_c

            Glide.with(itemView.context)
                .load("https:${hour.condition.icon}")
                .into(binding.imageRC)

            binding.imageRC.visibility = View.VISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.screen_box_element_fragment_third, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hour = hourList[position]
        holder.setData(hour)
    }

    override fun getItemCount(): Int {
        return hourList.size
    }

    /**
     * Обновление списка с условием, что время берётся только текущее и более позднее
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun updateWeatherList(newList: List<Hour>) {
        val dateStrings = newList.map { it.time }
        val filteredDateStrings = utilsApp.filterDates(dateStrings)
        val filteredList = newList.filter { hour ->
            filteredDateStrings.contains(hour.time)
        }
        hourList = filteredList
        notifyDataSetChanged()
    }
}