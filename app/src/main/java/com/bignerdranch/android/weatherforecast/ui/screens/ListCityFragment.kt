package com.bignerdranch.android.weatherforecast.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.weatherforecast.R
import com.bignerdranch.android.weatherforecast.data.database.BaseCity
import com.bignerdranch.android.weatherforecast.databinding.FragmentSecondBinding
import com.bignerdranch.android.weatherforecast.databinding.ScreenBoxElementBinding
import com.bignerdranch.android.weatherforecast.ui.recyclerView.RecyclerViewAdapter
import com.bignerdranch.android.weatherforecast.ui.viewModel.ListCityFragmentViewModel

class ListCityFragment : Fragment(), RecyclerViewAdapter.InfoItemClickListener {

    private lateinit var listCityFragmentViewModel: ListCityFragmentViewModel
    private lateinit var binding: FragmentSecondBinding
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var recycler: RecyclerView
    private val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        listCityFragmentViewModel = ViewModelProvider(this)[ListCityFragmentViewModel::class.java]

        recycler = binding.recyclerView
        recycler.layoutManager = LinearLayoutManager(context)

        adapter = RecyclerViewAdapter(emptyList())
        recycler.adapter = adapter

        adapter.setInfoListener(this)

        listCityFragmentViewModel.getInfoCityFromDatabase()
        listCityFragmentViewModel.liveDataListBaseCity.observe(
            viewLifecycleOwner
        ) { dataList ->
            dataList?.let {
                adapter.updateList(it)
            }
        }

        return binding.root
    }

    /**
     * Реализация метода интерфейса Adapter по нажатию на элемент списка
     */
    override fun onItemClickListener(baseCity: BaseCity) {
        bundle.putString("name", baseCity.name)
        bundle.putString("temp", baseCity.temp)
        bundle.putString("data", baseCity.date)

        findNavController().navigate(R.id.action_secondFragment_to_thirdFragment, bundle)
    }

    /**
     * Реализация метода интерфейса Adapter по нажатию на кнопку удаления элемента списка
     */
    override fun deleteElementClickListener(baseCity: BaseCity) {
        listCityFragmentViewModel.deleteCityForList(baseCity.name)
    }
}