package com.bignerdranch.android.weatherforecast.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.weatherforecast.databinding.FragmentSecondBinding
import com.bignerdranch.android.weatherforecast.databinding.ScreenBoxElementBinding
import com.bignerdranch.android.weatherforecast.ui.recyclerView.RecyclerViewAdapter
import com.bignerdranch.android.weatherforecast.ui.viewModel.ListCityFragmentViewModel

class ListCityFragment : Fragment() {

    private lateinit var listCityFragmentViewModel: ListCityFragmentViewModel
    lateinit var binding: FragmentSecondBinding
    lateinit var adapter: RecyclerViewAdapter
    lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        listCityFragmentViewModel = ViewModelProvider(this).get(ListCityFragmentViewModel::class.java)

        recycler = binding.recyclerView
        recycler.layoutManager = LinearLayoutManager(context)

//        adapter = RecyclerViewAdapter(emptyList())
//        recycler.adapter = adapter

        listCityFragmentViewModel.getCityInfo()
        listCityFragmentViewModel.resultResponse.observe(
            viewLifecycleOwner,
            Observer { dataList ->
                dataList?.let {
                    adapter = RecyclerViewAdapter(it)
                    recycler.adapter = adapter
                }
            })
        return binding.root
    }
}