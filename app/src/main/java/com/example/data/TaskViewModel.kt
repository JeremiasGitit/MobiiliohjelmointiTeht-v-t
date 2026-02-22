package com.example.data
import androidx.lifecycle.ViewModel
//import com.example.data.model.Task
import com.example.data.model.mockList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import repository.TaskRepository

import com.example.data.local.entity.Task

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
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Keskeneräisten tehtävien lukumäärä (näytetään yläpalkissa)
    val pendingCount: StateFlow<Int> = repository.pendingTaskCount
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
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

/*class TaskViewModel : ViewModel() {
    /*var tasks by mutableStateOf(listOf<Task>())
        private set

    var selectedTask by mutableStateOf<Task?>(null)
        private set*/

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.asStateFlow()

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask = _selectedTask.asStateFlow()

    init {
        _tasks.value = mockList
    }

    fun addTask(task: Task) {
        _tasks.value  += task
    }

    fun toggleDone(id: Int) {
        _tasks.value = _tasks.value.map {
            if (it.id == id) it.copy(done = !it.done)
            else it
        }
    }
    fun sortByPriority() {
        _tasks.value = _tasks.value.sortedBy { it.priority }
    }

    fun sortByDueDate() {
        _tasks.value = _tasks.value.sortedBy { it.dueDate }
    }

    fun filterDone() {
        _tasks.value = _tasks.value.filter { it.done }
    }
    fun removeTask(id: Int) {
        _tasks.value = _tasks.value.filter {it.id != id}
    }

    fun selectTask(task: Task) {
        _selectedTask.value = task
    }

    fun updateTask(updated: Task) {
        _tasks.value = _tasks.value.map {
            if (it.id == updated.id) updated else it
        }
        _selectedTask.value = null // sulje dialog päivityksen jälkeen
    }

    fun closeDialog() {
        _selectedTask.value = null
    }
}*/

