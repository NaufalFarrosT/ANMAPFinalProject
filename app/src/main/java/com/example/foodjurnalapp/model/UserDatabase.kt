package com.example.foodjurnalapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(User::class, FoodLog::class), version = 1)
abstract class UserDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun foodDao(): FoodDao

    companion object{
        @Volatile private var instance: UserDatabase?=null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                "foodjournaldb").build()

        operator fun invoke(context: Context){
            if(instance!=null){
                synchronized(LOCK){
                    instance?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}