package com.example.appscriptassignment.room_impl

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM users LIMIT :pageSize OFFSET :offset")
    fun getAllUsers(pageSize: Int, offset: Int): List<UserModel>

    @Query("SELECT * FROM users WHERE isFavorite = 1")
    fun getAllFavoriteUsers(): List<UserModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userModel: UserModel): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(list: List<UserModel>)

    @Delete
    fun delete(userModel: UserModel)

    @Update
    fun update(userModel: UserModel)
}