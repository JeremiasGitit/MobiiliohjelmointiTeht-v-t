package com.example.data.view
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.TaskViewModel
import com.example.data.model.Task
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox

/*
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
    val tasks by taskViewModel.tasks.collectAsState()
    val selectedTask by taskViewModel.selectedTask.collectAsState()

    Column(modifier = modifier.padding(16.dp)) {
        // Input field for new task
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Add task") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Task list
        LazyColumn(
            modifier = Modifier.weight(1f), // Fill remaining space
            verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
            items(tasks) { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable{
                            taskViewModel.selectTask(task)
                        }
                ) {
                    Text(
                        text = "${task.title} (${task.dueDate})",
                        modifier = Modifier.weight(1f)
                    )

                    //Checkbox for tasks and remove button
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = {
                            taskViewModel.toggleDone(task.id) // toggles the task done state
                        }
                    )

                    Button(onClick = { taskViewModel.removeTask(task.id)}) {
                        Text("Remove")
                    }
                }
            }
        }

        if (selectedTask != null) {
            DetailDialog(task = selectedTask!!,
                onClose = { taskViewModel.closeDialog() },
                onUpdate = { taskViewModel.updateTask(it) })
        }

        // Add task + sort buttons
        Column {
            Row {
                Button(
                    onClick = {
                        if (name.isNotBlank()) {
                            val newTask = Task(
                                id = tasks.size + 1,
                                title = name,
                                description = "Description",
                                priority = 1,
                                dueDate = "2026-12-31",
                                done = false
                            )
                            taskViewModel.addTask(newTask)
                            name = ""
                        }
                    }
                ) {
                    Text("Add new task")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = { taskViewModel.sortByDueDate() }) {
                    Text("Sort by due date")
                }

            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { taskViewModel.filterDone() }) {
                Text("Filter by done")
            }
        }
    }
}
*/

