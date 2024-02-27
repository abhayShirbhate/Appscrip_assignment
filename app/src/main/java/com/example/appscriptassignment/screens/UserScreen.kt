package com.example.appscriptassignment.screens

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appscriptassignment.room_impl.UserModel
import com.example.appscriptassignment.viewmodel.UserViewModel
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner


@Composable
fun UserScreen(viewModel: UserViewModel) {

    val users by remember(viewModel.listItems) {
        mutableStateOf(viewModel.listItems)
    }


    var isDialogVisible by remember {
        mutableStateOf(false)
    }
    val lifecycleOwner = LocalLifecycleOwner.current

    var message by remember {
        mutableStateOf(viewModel.errorLiveData.value)
    }

    viewModel.errorLiveData.observe(lifecycleOwner){errorMsg->
        message = errorMsg
        isDialogVisible = true
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        users.forEach { user ->

            swipeableContainer(user,viewModel)

            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    if (isDialogVisible){
        AlertDialogWithSingleButton(
            "Error",
            "",
            "Try Again"
        ){
            viewModel.fetchUsers()
            isDialogVisible = false
        }
    }
    if (viewModel.listItems.isEmpty())
        viewModel.fetchUsers()
}

