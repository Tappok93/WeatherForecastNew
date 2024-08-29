package com.bignerdranch.android.weatherforecast.ui.recyclerView

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.weatherforecast.R
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import com.bignerdranch.android.weatherforecast.databinding.ScreenBoxElementBinding

class RecyclerViewAdapter(private var myListArray: List<BaseCity>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bindingAdapter = ScreenBoxElementBinding.bind(itemView)
        val bundle = Bundle()

        /**
         * Заполнение шаблона данными, передача данных на следующий фрагмент
         */
        fun setData(baseCity: BaseCity) {
            bindingAdapter.resultDataTV.text = baseCity.date
            bindingAdapter.resultCityTV.text = baseCity.name
            bindingAdapter.resultTempTV.text = baseCity.temp

            bindingAdapter.constraintElement.setOnClickListener {

                bundle.putString("name", bindingAdapter.resultCityTV.text as String)
                bundle.putString("temp", bindingAdapter.resultTempTV.text as String)
                bundle.putString("data", bindingAdapter.resultDataTV.text as String)

                Navigation.findNavController(it)
                    .navigate(R.id.action_secondFragment_to_thirdFragment, bundle)
            }
        }
    }

    /**
     * Создание холдера по созданному XML элементу
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.screen_box_element, parent, false)
        return ItemViewHolder(inflater)
    }

    /**
     * Счётчик количеста отображаемых элементов в списке
     */
    override fun getItemCount(): Int {
        return myListArray.size
    }

    /**
     * Заполнение холдера данными по созданному шаблону XML
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setData(myListArray[position])
    }

    /**
     * Обновление созданного списка
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(listItem: List<BaseCity>) {
        myListArray = listItem
        notifyDataSetChanged()
    }
}