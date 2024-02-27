package com.example.appscriptassignment.listeners

import com.example.appscriptassignment.room_impl.UserModel
import kotlinx.coroutines.flow.Flow

interface GetFavoriteUserLocalDBListener {
    fun onGetFavoriteUserCallBack(list: List<UserModel>)
}