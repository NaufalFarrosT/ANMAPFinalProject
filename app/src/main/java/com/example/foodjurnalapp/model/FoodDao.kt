package com.example.foodjurnalapp.model

import androidx.room.*

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg foodlog:FoodLog)

    @Query("SELECT * FROM foodlog WHERE userID= :uid")
    suspend fun selectAllFood(uid:Int): List<FoodLog>

    @Query("SELECT * FROM foodlog WHERE fid= :id")
    suspend fun selectFood(id:Int): FoodLog

    @Delete
    suspend fun deleteFoodLog(foodLog:FoodLog)
}