package com.bignerdranch.android.weatherforecast.data.repository.repositoryDatabase

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.weatherforecast.R


class RecyclerViewAdapter(myList: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    private val myListArray = myList

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvCityName = itemView.findViewById<TextView>(R.id.cityTV)

        fun setData(nameCity: String) {

            tvCityName.text = nameCity
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater.inflate(R.layout.screen_box_element, parent, false))
    }

    override fun getItemCount(): Int {
        return myListArray.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setData(myListArray.get(position))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(listItem:List<String>) {

        myListArray.clear()
        myListArray.addAll(listItem)
        notifyDataSetChanged()
    }
}