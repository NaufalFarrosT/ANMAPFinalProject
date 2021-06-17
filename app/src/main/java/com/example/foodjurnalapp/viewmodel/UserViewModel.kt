package com.example.foodjurnalapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.example.foodjurnalapp.model.User
import com.example.foodjurnalapp.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application)
    :AndroidViewModel(application), CoroutineScope {
    private val job = Job()

    fun addUser(list: List<User>){
        launch {
            val db = Room.databaseBuilder(
                    getApplication(), UserDatabase::class.java,
                    "newuserdb").build()
            db.userDao().insertAll(*list.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}