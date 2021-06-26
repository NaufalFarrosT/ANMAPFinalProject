package com.example.foodjurnalapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo(name="name")
    var name:String,
    @ColumnInfo(name="age")
    var age:Int,
    @ColumnInfo(name="gender")
    var gender:String,
    @ColumnInfo(name="weight")
    var weight:Int,
    @ColumnInfo(name="height")
    var height:Int,
    @ColumnInfo(name="goal")
    var title:String
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}

@Entity
data class FoodLog(
    @ColumnInfo(name="name")
    var name:String,
    @ColumnInfo(name="calories")
    var cal:Int,
    @ColumnInfo(name="date")
    var date:String,
    @ColumnInfo(name="userID")
    var userID:Int
){
    @PrimaryKey(autoGenerate = true)
    var fid:Int=0
}