package com.example.appscriptassignment.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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

    private val _listItems = mutableStateListOf<UserModel>()
    val listItems: List<UserModel> get() = _listItems


    private var _progressBarVisibility =  mutableStateOf(false)
    val progressBarVisibility get() = _progressBarVisibility


    private var pageNo = 1
    private val pageSize = 10

    init {
        fetchUsers()
    }

    fun fetchUsers(){
        if (pageNo==-1) return
        _progressBarVisibility.value = true
        repository.fetchUsers(pageNo,pageSize,this)
    }

    fun updateUserToLocal(userModel: UserModel){
        repository.updateUserToLocalDB(userModel)
    }


    override fun fetchUserApiListenerCallBack(result: ApiResult<List<User>>) {
        when (result) {
            is ApiResult.Success -> {
                if (result.data.size < 10) pageNo = -1
                repository.saveUserListToLocalDB(result.data){
                    repository.getAllUsersFromLocalDB(pageSize,pageNo,this)
                    pageNo++
                }
            }

            is ApiResult.NoInternetConnection -> {
                _errorLiveDate.postValue(result.msg)
                _progressBarVisibility.value = false
            }

            is ApiResult.Error -> {
                _progressBarVisibility.value = false
                _errorLiveDate.postValue(result.msg)
            }
        }
    }

    override fun getAllUsersFromLocalDB(list: List<UserModel>) {
        _listItems.addAll(list)
        _progressBarVisibility.value = false
    }


}