package com.example.appscriptassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appscriptassignment.screens.DashboardScreen
import com.example.appscriptassignment.ui.theme.AppScriptAssignmentTheme
import com.example.appscriptassignment.viewmodel.FavoriteViewModel
import com.example.appscriptassignment.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScriptAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val userViewModel: UserViewModel by viewModels()
                    val favoriteViewModel: FavoriteViewModel by viewModels()

                    DashboardScreen( userViewModel,favoriteViewModel)
                }
            }
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppScriptAssignmentTheme {
    }
}