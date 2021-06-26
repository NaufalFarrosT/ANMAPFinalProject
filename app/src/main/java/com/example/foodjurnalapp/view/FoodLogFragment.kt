package com.example.foodjurnalapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodjurnalapp.R
import com.example.foodjurnalapp.viewmodel.FoodLogListViewModel
import kotlinx.android.synthetic.main.fragment_food_log.*

class FoodLogFragment : Fragment() {
    private lateinit var viewModel:FoodLogListViewModel
    private val foodLogListAdapter = FoodLogListAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FoodLogListViewModel::class.java)
        viewModel.refresh(1)
        recViewFoodLog.layoutManager = LinearLayoutManager(context)
        recViewFoodLog.adapter = foodLogListAdapter

        fabAdd.setOnClickListener {
            val action = FoodLogFragmentDirections.actionAddMealFragment()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.foodLogLD.observe(viewLifecycleOwner, Observer {
            foodLogListAdapter.updateFoodLogList(it)
        })
    }
}