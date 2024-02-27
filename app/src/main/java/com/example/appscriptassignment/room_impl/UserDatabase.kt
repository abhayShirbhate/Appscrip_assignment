package com.example.appscriptassignment.room_impl

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import java.lang.Exception


@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "user_database"




        fun getUserDatabase(applicationContext: Context): UserDatabase {
            var postDatabase: UserDatabase? = null
            try {
                postDatabase = Room.databaseBuilder(
                    applicationContext,
                    UserDatabase::class.java,
                    DATABASE_NAME
                ).build()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return postDatabase!!
        }
    }

}