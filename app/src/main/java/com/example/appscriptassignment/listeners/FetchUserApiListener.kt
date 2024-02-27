package com.example.appscriptassignment.listeners

import com.example.appscriptassignment.Response.ApiResult
import com.example.appscriptassignment.models.User
import com.example.appscriptassignment.room_impl.UserModel

interface FetchUserApiListener {
    fun fetchUserApiListenerCallBack(result: ApiResult<List<User>>)
    fun getAllUsersFromLocalDB(list: List<UserModel>)
}