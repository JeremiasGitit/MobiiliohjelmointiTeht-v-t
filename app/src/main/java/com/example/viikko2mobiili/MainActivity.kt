package com.example.viikko2mobiili

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.viikko2mobiili.ui.theme.Viikko2mobiiliTheme
import com.example.viikko2mobiili.domain.addTask
import com.example.viikko2mobiili.domain.Task
import com.example.viikko2mobiili.domain.mockList
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko2mobiili.domain.sortTasksByPriority
import com.example.viikko2mobiili.ui.theme.Viikko2mobiiliTheme
import com.example.viikko2mobiili.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikko2mobiiliTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    HomeScreen()
                }
            }
        }
    }
}


/*class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikko2mobiiliTheme()  {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    /* Text(
         text = "Hello $name!",
         modifier = modifier
     )*/
}
@Composable
fun NameTextField(
    name: String,
    onNameChange: (String) -> Unit
) {

    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = {Text("Add task")},
        modifier = Modifier
            .padding(top = 16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ParentComponent() {
    var name by remember { mutableStateOf("") }
    var taskList by remember { mutableStateOf(mockList) }

    Column {
        NameTextField(
            name = name,
            onNameChange = { name = it}
        )
        // Text(" $name")
        Spacer(modifier = Modifier.height(height = 16.dp))
        taskList.forEach {task ->
            Text("${task.id} ${task.title} ${task.dueDate} ")
        }

        Row() {
            Button(
                onClick = {
                    val newTask = Task(
                        id = taskList.size + 1,
                        title = name,
                        description = "Description",
                        priority = 1,
                        dueDate = "2026-09-30",
                        done = false,
                    )
                    taskList = addTask(taskList, newTask)

                },
                content = {
                    Text("Add new task")
                }
            )
            Button(
                onClick = {
                    taskList = taskList.sortedBy { it.dueDate }
                },
                content = {
                    Text("Sort by due date")
                }
            )
        }
    }
}
*/
