package com.example.foodjurnalapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.foodjurnalapp.model.FoodLog
import com.example.foodjurnalapp.model.UserDatabase
import com.example.foodjurnalapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FoodLogListViewModel(application:Application)
    :AndroidViewModel(application), CoroutineScope {

    val foodLogLD = MutableLiveData<List<FoodLog>>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh(uid:Int){
        launch {
            val db = buildDb(getApplication())
            foodLogLD.value = db.foodDao().selectAllFood(uid)
        }
    }
}