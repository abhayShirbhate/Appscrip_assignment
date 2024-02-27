package com.example.appscriptassignment.retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object RetrofitClient {
    private const val BASE_URL = "https://gorest.co.in/public/v2/"

    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @ViewModelScoped
    @Provides
    fun provideUserService(): UserService{
        return getRetrofitInstance().create(UserService::class.java)
    }

}