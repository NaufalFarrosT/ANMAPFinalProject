package com.example.foodjurnalapp.util

import android.content.Context
import androidx.room.Room
import com.example.foodjurnalapp.model.UserDatabase

val DB_NAME="foodjournaldb"

fun buildDb(context: Context):UserDatabase{
    val db = Room.databaseBuilder(context, UserDatabase::class.java, DB_NAME).build()
    return db
}