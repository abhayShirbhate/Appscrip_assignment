package com.example.appscriptassignment.retrofit

import com.example.appscriptassignment.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("users")
    fun getUsers(
        @Query("page")  page: Int,
        @Query("per_page")  pageSize: Int
    ): Call<List<User>>
}