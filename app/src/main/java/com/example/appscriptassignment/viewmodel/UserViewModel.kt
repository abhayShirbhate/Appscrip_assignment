package com.example.appscriptassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appscriptassignment.Response.ApiResult
import com.example.appscriptassignment.listeners.FetchUserApiListener
import com.example.appscriptassignment.models.User
import com.example.appscriptassignment.repository.UserRepository
import com.example.appscriptassignment.room_impl.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository): ViewModel(),FetchUserApiListener {

    private val _errorLiveDate = MutableLiveData<String>()
    val errorLiveData get() = _errorLiveDate

    private val _listItems = mutableListOf<UserModel>()
    val listItems: List<UserModel> get() = _listItems.toList()

    fun fetchUsers(){
        repository.fetchUsers(1,10,this)
    }

    fun updateUserToLocal(userModel: UserModel){
        repository.updateUserToLocalDB(userModel)
    }


    override fun fetchUserApiListenerCallBack(result: ApiResult<List<User>>) {
        when (result) {
            is ApiResult.Success -> {
                repository.saveUserListToLocalDB(result.data){
                    repository.getAllUsersFromLocalDB(this)
                }
            }

            is ApiResult.NoInternetConnection -> {
                _errorLiveDate.postValue(result.msg)
            }

            is ApiResult.Error -> {
                _errorLiveDate.postValue(result.msg)
            }
        }
    }

    override fun getAllUsersFromLocalDB(list: List<UserModel>) {
        _listItems.addAll(list)
    }


}