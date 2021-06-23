package com.example.foodjurnalapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FoodLog::class), version = 1)
abstract class FoodDatabase:RoomDatabase() {
    abstract fun foodDao():FoodDao

    companion object{
        @Volatile private var instance:FoodDatabase?=null
        private val LOCK = Any()

        private fun buildDatabase(context:Context)=
            Room.databaseBuilder(
                context.applicationContext,
                FoodDatabase::class.java,
                "foodlogdb").build()

        operator fun invoke(context: Context){
            if(instance!=null){
                synchronized(LOCK){
                    instance?: buildDatabase(context).also {
                        instance=it
                    }
                }
            }
        }
    }
}