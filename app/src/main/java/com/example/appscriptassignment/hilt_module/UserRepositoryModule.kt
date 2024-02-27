package com.example.appscriptassignment.hilt_module

import com.example.appscriptassignment.repository.UserRepository
import com.example.appscriptassignment.repository.UserRepositoryImpl
import com.example.appscriptassignment.retrofit.UserService
import com.example.appscriptassignment.room_impl.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UserRepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideEmployeeRepository(
        employeeService: UserService,
        userDao: UserDao
    ): UserRepository {
        return UserRepositoryImpl(employeeService,userDao)
    }
}