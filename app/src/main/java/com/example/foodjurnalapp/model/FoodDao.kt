package com.example.foodjurnalapp.model

import androidx.room.*

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg foodlog:FoodLog)

    @Query("SELECT * FROM foodlog")
    suspend fun selectAllFood(): List<FoodLog>

    @Query("SELECT * FROM foodlog WHERE uuid= :id")
    suspend fun selectFood(id:Int): FoodLog

    @Delete
    suspend fun deleteUser(user:User)
}