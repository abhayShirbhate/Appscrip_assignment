package com.example.appscriptassignment.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appscriptassignment.viewmodel.UserViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner


@Composable
fun UserScreen(viewModel: UserViewModel) {

    val users by remember(viewModel.listItems) {
        mutableStateOf(viewModel.listItems)
    }

    val progressBarVisibility by viewModel.progressBarVisibility


    var isAlertDialogVisible by remember {
        mutableStateOf(false)
    }
    val lifecycleOwner = LocalLifecycleOwner.current

    var message by remember {
        mutableStateOf(viewModel.errorLiveData.value)
    }

    viewModel.errorLiveData.observe(lifecycleOwner){errorMsg->
        message = errorMsg
        isAlertDialogVisible = true
    }



    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(users.size) { i ->
            val user = users[i]
            swipeableContainer(user,viewModel)

            Spacer(modifier = Modifier.height(8.dp))

        }
        item {
                if ( !progressBarVisibility) {
                    viewModel.fetchUsers()
                }


        }
    }

    if (isAlertDialogVisible){
        AlertDialogWithSingleButton(
            "Error",
            "",
            "Try Again"
        ){
            viewModel.fetchUsers()
            isAlertDialogVisible = false
        }
    }
    if (progressBarVisibility){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
                CircularProgressIndicator(
                    color = Color.Blue,
                    strokeWidth = 2.dp
                )

        }
    }


}

