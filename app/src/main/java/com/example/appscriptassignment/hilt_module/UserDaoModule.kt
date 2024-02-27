package com.example.appscriptassignment.hilt_module

import android.content.Context
import com.example.appscriptassignment.room_impl.UserDao
import com.example.appscriptassignment.room_impl.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UserDaoModule {

    @ViewModelScoped
    @Provides
    fun provideUserDao(
        @ApplicationContext context: Context
    ): UserDao {
        return UserDatabase.getUserDatabase(context).userDao()
    }
}