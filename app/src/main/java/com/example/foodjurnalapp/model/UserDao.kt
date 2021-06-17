package com.example.foodjurnalapp.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user:User)

    @Query("SELECT * FROM user")
    suspend fun selectAllUser(): List<User>

    @Query("SELECT * FROM user WHERE uuid= :id")
    suspend fun selectUser(id:Int): User

    @Delete
    suspend fun deleteUser(user:User)
}