package com.example.appscriptassignment.room_impl

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name= "name")
    val name: String,
    @ColumnInfo(name= "email")
    val email: String,
    @ColumnInfo(name= "gender")
    val gender: String,
    @ColumnInfo(name= "status")
    val status: String,
    @ColumnInfo(name= "isFavorite")
    var isFavorite: Boolean = false
)