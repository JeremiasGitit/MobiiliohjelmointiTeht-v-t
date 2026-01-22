package com.example.viikko2mobiili.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko2mobiili.TaskViewModel
import com.example.viikko2mobiili.domain.Task

@Composable
fun HomeScreen(
    taskViewModel: TaskViewModel = viewModel()
) {
    val tasks = taskViewModel.tasks

    Column(modifier = Modifier.padding(16.dp)) {

        tasks.forEach { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(
                    text = "${task.title} (${task.dueDate})",
                    modifier = Modifier.weight(1f)
                )

                Button(onClick = {
                    taskViewModel.toggleDone(task.id)
                }) {
                    Text("Toggle")
                }
            }
        }
    }
}