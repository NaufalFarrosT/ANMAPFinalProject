package com.example.foodjurnalapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.foodjurnalapp.model.User
import com.example.foodjurnalapp.model.UserDatabase
import com.example.foodjurnalapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application)
    :AndroidViewModel(application), CoroutineScope {
    private val job = Job()

    val userLD = MutableLiveData<User>()
    val userID = MutableLiveData<Int>()

    fun addUser(list: List<User>){
        launch {
            val db = buildDb(getApplication())
            db.userDao().insertAll(*list.toTypedArray())
        }
    }

    fun fetch(uid:Int){
        launch {
            val db = buildDb(getApplication())
            userLD.value = db.userDao().selectUser(uid)
        }
    }

    fun getUserID(user:User){
        launch {
            val db = buildDb(getApplication())
            var check = 0
            var listUser = db.userDao().selectAllUser()
            for(x in listUser){
                if(user.name == x.name && user.age == x.age && user.gender == x.gender && user.weight == x.weight){
                    check = 1
                    userID.value = x.uuid
                }
            }

            if(check == 0){
                val list = listOf(user)
                addUser(list)
                //uid=db.userDao().getUser(user.name, user.age, user.gender, user.weight, user.height).uuid
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}