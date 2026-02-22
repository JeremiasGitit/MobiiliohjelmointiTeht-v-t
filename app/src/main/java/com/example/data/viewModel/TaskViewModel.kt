package com.example.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.entity.Task
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import repository.TaskRepository

class TaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {

    // Flow → StateFlow muunnos:
    // .stateIn() muuttaa "kylmän" Flow:n "kuumaksi" StateFlow:ksi
    // - viewModelScope = elinkaari (peruutetaan kun ViewModel tuhotaan)
    // - SharingStarted.WhileSubscribed(5000) = pysyy aktiivisena 5s UI:n poistumisen jälkeen
    // - emptyList() = alkuarvo ennen kuin tietokannasta saadaan data
    val allTasks: StateFlow<List<Task>> = repository.allTasks
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Keskeneräisten tehtävien lukumäärä (näytetään yläpalkissa)
    val pendingCount: StateFlow<Int> = repository.pendingTaskCount
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5000),
            initialValue = 0
        )

    // UI kutsuu tätä kun käyttäjä lisää uuden tehtävän
    // viewModelScope.launch käynnistää korutiinin taustasäikeessä
    // → UI ei jumitu tietokantaoperaation aikana
    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            val task = Task(
                title = title,
                description = description
            )
            repository.insert(task)
            // Flow päivittää UI:n automaattisesti insertion jälkeen!
        }
    }

    // Vaihda tehtävän tila: valmis ↔ keskeneräinen
    // .copy() luo uuden olion muutetulla arvolla (data class)
    fun toggleTask(task: Task) {
        viewModelScope.launch {
            val updated = task.copy(isCompleted = !task.isCompleted)
            repository.update(updated)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }

    // Poista kaikki valmiit tehtävät kerralla
    fun deleteCompletedTasks() {
        viewModelScope.launch {
            repository.deleteCompletedTasks()
        }
    }
}