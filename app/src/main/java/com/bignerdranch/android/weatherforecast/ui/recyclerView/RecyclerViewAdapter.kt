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
import com.bignerdranch.android.weatherforecast.ui.viewModel.ListCityFragmentViewModel

class RecyclerViewAdapter(private var myListArray: List<BaseCity>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    //Обработать нажатие в Adapter
    private var infoListener: InfoItemClickListener? = null

    fun setInfoListener(listener: InfoItemClickListener) {
        infoListener = listener
    }

    interface InfoItemClickListener {
        fun onItemClickListener(baseCity: BaseCity)
        fun deleteElementClickListener(baseCity: BaseCity)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bindingAdapter = ScreenBoxElementBinding.bind(itemView)

        /**
         * Заполнение шаблона данными, передача данных на следующий фрагмент, удаление элемента списка
         */
        fun setData(baseCity: BaseCity, listener: InfoItemClickListener?) {
            bindingAdapter.resultDataTV.text = baseCity.date
            bindingAdapter.resultCityTV.text = baseCity.name
            bindingAdapter.resultTempTV.text = baseCity.temp

//            bindingAdapter.constraintElement.setOnClickListener {
//                listener?.onItemClickListener(baseCity)
//            }

            bindingAdapter.infoBTN.setOnClickListener {
                listener?.onItemClickListener(baseCity)
            }

            bindingAdapter.deleteEmgView.setOnClickListener {
                listener?.deleteElementClickListener(baseCity)
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
        val baseCityItem = myListArray[position]
        holder.setData(baseCityItem, infoListener)
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