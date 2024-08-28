package com.bignerdranch.android.weatherforecast.ui.recyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.weatherforecast.R
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import com.bignerdranch.android.weatherforecast.databinding.ScreenBoxElementBinding

class RecyclerViewAdapter(private var myListArray: List<BaseCity>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {


    class ItemViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val bindingAdapter = ScreenBoxElementBinding.bind(itemView)

        fun setData(baseCity: BaseCity) {
            bindingAdapter.resultDataTV.text = baseCity.date
            bindingAdapter.resultCityTV.text = baseCity.name
            bindingAdapter.resultTempTV.text = baseCity.temp
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.screen_box_element, parent, false)
        return ItemViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return myListArray.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setData(myListArray[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(listItem: List<BaseCity>) {
        myListArray = listItem
        notifyDataSetChanged()
    }
}