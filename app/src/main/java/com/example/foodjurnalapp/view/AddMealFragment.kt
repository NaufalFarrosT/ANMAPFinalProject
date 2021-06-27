package com.example.foodjurnalapp.view

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.foodjurnalapp.R
import com.example.foodjurnalapp.model.FoodLog
import com.example.foodjurnalapp.viewmodel.FoodLogDetailViewModel
import kotlinx.android.synthetic.main.fragment_add_meal.*
import java.util.*

class AddMealFragment : Fragment() {
    private lateinit var viewModel:FoodLogDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_meal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set current date
        val sdf = java.text.SimpleDateFormat("dd/M/yyyy")
        val currentDate=sdf.format(Date())
        txtDateAddMeal.setText(currentDate)

        viewModel = ViewModelProvider(this).get(FoodLogDetailViewModel::class.java)

        btnAddMeal.setOnClickListener {
            var foodLog = FoodLog(txtAddMealName.text.toString(), txtAddMealCal.text.toString().toInt(), currentDate,2)
            val list = listOf(foodLog)
            viewModel.addFoodLog(list)
            Toast.makeText(view.context, "Data Added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}