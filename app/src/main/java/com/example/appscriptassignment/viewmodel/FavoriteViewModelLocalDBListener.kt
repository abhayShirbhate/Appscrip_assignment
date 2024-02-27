package com.example.appscriptassignment.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appscriptassignment.listeners.GetFavoriteUserLocalDBListener
import com.example.appscriptassignment.repository.UserRepository
import com.example.appscriptassignment.room_impl.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: UserRepository): ViewModel(),GetFavoriteUserLocalDBListener {

    private val _userListFlow = MutableStateFlow<List<UserModel>>(emptyList())
    val userListFlow: Flow<List<UserModel>> get() = _userListFlow

    fun getAllFavoriteUsers(){
        repository.getAllFavoriteUsers(this)
    }

    override fun onGetFavoriteUserCallBack(list: List<UserModel>) {
        _userListFlow.value = list
    }
}