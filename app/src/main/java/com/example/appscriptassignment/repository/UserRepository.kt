package com.example.appscriptassignment.repository

import com.example.appscriptassignment.listeners.FetchUserApiListener
import com.example.appscriptassignment.listeners.GetFavoriteUserLocalDBListener
import com.example.appscriptassignment.models.User
import com.example.appscriptassignment.room_impl.UserModel

interface UserRepository {
    fun fetchUsers(page: Int, pageSize: Int, fetchUserApiListener: FetchUserApiListener)
    fun saveUserListToLocalDB(userList: List<User>,callBack: ()->Unit)
    fun updateUserToLocalDB(userModel: UserModel)
    fun getAllUsersFromLocalDB(pageSize: Int, pageNo:Int,fetchUserApiListener: FetchUserApiListener)
    fun getAllFavoriteUsers(getFavoriteUserLocalDBListener: GetFavoriteUserLocalDBListener)
}