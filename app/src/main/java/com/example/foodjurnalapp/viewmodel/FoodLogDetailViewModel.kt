package com.example.foodjurnalapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.example.foodjurnalapp.model.FoodDatabase
import com.example.foodjurnalapp.model.FoodLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FoodLogDetailViewModel(application: Application)
    :AndroidViewModel(application), CoroutineScope {
    private val job = Job()

    fun addFoodLog(list: List<FoodLog>){
        launch {
            val db = Room.databaseBuilder(
                    getApplication(), FoodDatabase::class.java,
                    "foodlogdb").build()
            db.foodDao().insertAll(*list.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}