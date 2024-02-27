package com.example.appscriptassignment.repository

import com.example.appscriptassignment.Response.ApiResult
import com.example.appscriptassignment.listeners.FetchUserApiListener
import com.example.appscriptassignment.listeners.GetFavoriteUserLocalDBListener
import com.example.appscriptassignment.models.User
import com.example.appscriptassignment.retrofit.UserService
import com.example.appscriptassignment.room_impl.UserDao
import com.example.appscriptassignment.room_impl.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val userDao: UserDao
) :
    UserRepository {

    override fun fetchUsers(page: Int, pageSize: Int, fetchUserApiListener: FetchUserApiListener) {
        userService.getUsers(page, pageSize).enqueue(object : Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    fetchUserApiListener.fetchUserApiListenerCallBack(ApiResult.Success(response.body()!!))
                } else fetchUserApiListener.fetchUserApiListenerCallBack(ApiResult.Error("Something went wrong, please try again!! \n responseCode ${response.code()}"))

            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                if (t is SocketTimeoutException || t is UnknownHostException) {
                    fetchUserApiListener.fetchUserApiListenerCallBack(
                        ApiResult.NoInternetConnection(
                            "Please check internet connection and try again!!"
                        )
                    )
                } else fetchUserApiListener.fetchUserApiListenerCallBack(ApiResult.Error("Something went wrong, please try again!!"))
            }
        })
    }

    override fun getAllUsersFromLocalDB(fetchUserApiListener: FetchUserApiListener) {
        CoroutineScope(Dispatchers.IO).launch {
            val list = userDao.getAllUsers()
            withContext(Dispatchers.Main) {
                fetchUserApiListener.getAllUsersFromLocalDB(list)
            }
        }
    }

    override fun saveUserListToLocalDB(userList: List<User>, callBack: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val userModelList = List(userList.size) { i ->
                val user = userList[i]
                UserModel(
                    user.id,
                    user.name,
                    user.email,
                    user.gender,
                    user.status,
                    false
                )
            }
            userDao.insertAll(userModelList)
            withContext(Dispatchers.Main){
                callBack()
            }
        }
    }

    override fun updateUserToLocalDB(userModel: UserModel) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.update(userModel)
        }
    }

    override fun getAllFavoriteUsers(getFavoriteUserLocalDBListener: GetFavoriteUserLocalDBListener) {
        CoroutineScope(Dispatchers.IO).launch {
            val list = userDao.getAllFavoriteUsers()
            withContext(Dispatchers.Main) {
                getFavoriteUserLocalDBListener.onGetFavoriteUserCallBack(list)
            }
        }
    }
}