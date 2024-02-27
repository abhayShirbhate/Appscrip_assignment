package com.example.appscriptassignment.screens

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AlertDialogWithSingleButton(
    title: String,
    description: String,
    positiveButtonText: String,
    onPositiveButtonClick: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { /* Handle dialog dismiss if needed */ },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = description)
        },
        confirmButton = {
            Button(
                onClick = {
                    onPositiveButtonClick()
                }
            ) {
                Text(text = positiveButtonText)
            }
        }

    )
}