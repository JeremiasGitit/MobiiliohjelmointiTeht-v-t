package com.example.data

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.data.ui.theme.Viikko2mobiiliTheme
//import com.example.data.view.HomeScreen

import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.view.TaskListScreen

import com.example.data.local.AppDatabase
import repository.TaskRepository
import com.example.data.viewModel.TaskViewModel

class MainActivity : ComponentActivity() {
    // Luo tietokanta lazy-patternilla (vasta kun sitä tarvitaan)
    private val database by lazy {
        AppDatabase.getDatabase(applicationContext)
    }

    // Luo Repository, joka käyttää DAO:a tietokantaoperaatioihin
    private val repository by lazy {
        TaskRepository(database.taskDao())
    }

    // Luo ViewModel ViewModelProvider.Factory:n avulla
    // Factory tarvitaan koska ViewModel ottaa parametrin (repository)
    private val viewModel: TaskViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TaskViewModel(repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContent käynnistää Compose-UI:n
        setContent {
            Viikko2mobiiliTheme {
                // Annetaan ViewModel näkymälle
                TaskListScreen(viewModel = viewModel)
            }
        }
    }
}

/*class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Viikko2mobiiliTheme {

                    HomeScreen(modifier = Modifier.fillMaxSize().padding(16.dp))
                //WeatherScreen()
            }
        }
    }
}*/