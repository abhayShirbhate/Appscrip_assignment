package com.example.appscriptassignment.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appscriptassignment.room_impl.UserModel
import com.example.appscriptassignment.viewmodel.FavoriteViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun FavoritePage(viewModel:FavoriteViewModel) {
    val userList: List<UserModel> by viewModel.userListFlow.collectAsState(initial = emptyList())


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        userList.forEach { user ->
            UserListItem(user)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }


    viewModel.getAllFavoriteUsers()
}