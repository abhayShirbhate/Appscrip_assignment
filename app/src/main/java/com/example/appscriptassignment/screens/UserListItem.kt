package com.example.appscriptassignment.screens


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.IconSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.appscriptassignment.room_impl.UserModel
import kotlinx.coroutines.delay
import com.example.appscriptassignment.swipe.SwipeAction
import com.example.appscriptassignment.swipe.SwipeableActionsBox
import com.example.appscriptassignment.viewmodel.UserViewModel

@Composable
fun UserListItem(user: UserModel) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,

        ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Gender: ${user.gender}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(if (user.status == "active") Color.Green else Color.Red)
                    .offset(x = 8.dp, y = 8.dp)
                    .padding(4.dp)
            )


        }
    }
}


@Composable
fun swipeableContainer(userModel: UserModel, viewModel: UserViewModel) {

    val user by remember { mutableStateOf(userModel) }


    val favorite = SwipeAction(
        icon = {
            defaultIcon(
                icon = Icons.Default.Favorite,
                title = "Make favorite",
                onclick = {
                }
            )
        },
        background = Color.Green,
        onSwipe = {
            user.isFavorite = true
            viewModel.updateUserToLocal(user)
                  },
        weight = 1.0,
        isUndo = false
    )

    val favoriteBoarder = SwipeAction(
        onSwipe = {
            user.isFavorite = false
            viewModel.updateUserToLocal(user)
                  },
        icon = {
            defaultIcon(
                icon = Icons.Default.FavoriteBorder,
                title = "Remove as favorite",
                onclick = {
                }
            )
        },
        background = Color.Red,
        weight = 1.0,
        isUndo = false
    )

    SwipeableActionsBox(
        startActions = listOf(favorite, favoriteBoarder),
        endActions = listOf(favorite, favoriteBoarder),
        dragStateDelay = 1,
        userModel = user
    ) {
        UserListItem(user)
    }
}

@Composable
fun defaultIcon(
    icon: ImageVector,
    title: String,
    onclick: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(0.5F)
                .clickable {
                    onclick()
                }
        )
        Text(
            text = title,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}


@Preview
@Composable
fun PreviewUserListItem() {
    UserListItem(
        UserModel(
            id = 1,
            name = "John Doe",
            email = "john@example.com",
            gender = "male",
            status = "active"
        )
    )
}
