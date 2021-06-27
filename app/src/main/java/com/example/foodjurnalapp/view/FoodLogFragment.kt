package com.example.foodjurnalapp.view

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
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
import com.example.foodjurnalapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_food_log.*
import java.util.*

class FoodLogFragment : Fragment() {
    private lateinit var viewModel:FoodLogListViewModel
    private lateinit var viewModelUser:UserViewModel
    private val foodLogListAdapter = FoodLogListAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uuid = FoodLogFragmentArgs.fromBundle(requireArguments()).uID

        val sdf = java.text.SimpleDateFormat("dd/M/yyyy")
        val currentDate=sdf.format(Date())
        txtCurrentDate.setText(currentDate)

        viewModel = ViewModelProvider(this).get(FoodLogListViewModel::class.java)
        viewModelUser = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModelUser.fetch(uuid)
        viewModel.refresh(uuid)

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
            var total=0
            for(x in it){
                total+=x.cal
            }

            txtTotalCalories.setText(total.toString())
        })

        viewModelUser.userLD.observe(viewLifecycleOwner, Observer {
            txtGenderBOD.setText(it.gender+", "+it.age.toString()+" years old")
            txtNameFoodLog.setText(it.name)

            var target=0.0
            if(it.gender == "Male"){
                target = 13.397 * it.weight + 4.779 * it.height - 5.677 * it.height + 88.362
            }else{
                target = 9.247 * it.weight + 3.098 * it.height - 4.33 * it.height + 447.593
            }

            if(it.title == "Loss Weight"){
                target -= (target * 0.15)
            }else if(it.title == "Gain Weight"){
                target += (target * 0.15)
            }

            txtTargetCalories.setText(target.toString() + " Cal")
        })
    }
}