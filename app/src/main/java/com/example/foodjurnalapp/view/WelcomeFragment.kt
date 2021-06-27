package com.example.foodjurnalapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.foodjurnalapp.R
import com.example.foodjurnalapp.model.User
import com.example.foodjurnalapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : Fragment() {
    private lateinit var viewModel:UserViewModel
    var uID=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        btnStartTheJourney.setOnClickListener {
            var radio = view.findViewById<RadioButton>(radioGroupGoal.checkedRadioButtonId)
            var user = User(txtName.text.toString(), txtAge.text.toString().toInt(),txtGender.text.toString(),
                        txtWeight.text.toString().toInt(), txtHeight.text.toString().toInt(), radio.text.toString())

            viewModel.getUserID(user)

            Toast.makeText(view.context, "Login Success", Toast.LENGTH_LONG).show()

            val action = WelcomeFragmentDirections.actionFoodLogFragment(2)
            Navigation.findNavController(it).navigate(action)
        }
    }
}