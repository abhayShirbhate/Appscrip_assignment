package com.example.appscriptassignment.screens

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.appscriptassignment.viewmodel.UserViewModel
import com.example.appscriptassignment.viewmodel.FavoriteViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardScreen(userViewModel: UserViewModel,favoriteViewModel: FavoriteViewModel) {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {

            BottomNavigation(
                backgroundColor = Color.White,
                elevation = 8.dp,
            ) {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Users") },
                    label = { Text("Users") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )

                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favourite") },
                    label = { Text("Favourite") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )

                BottomNavigationItem(
                    icon = { Icon(Icons.Default.CheckCircle, contentDescription = "To-Do") },
                    label = { Text("To-Do") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )

                BottomNavigationItem(
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 }
                )
            }
        }
    ) {
        when (selectedTab) {
            0 -> UserScreen(userViewModel)
            1 -> FavoritePage(favoriteViewModel)
            2 -> ToDoPage()
            3 -> ProfilePage()
        }
    }
}









