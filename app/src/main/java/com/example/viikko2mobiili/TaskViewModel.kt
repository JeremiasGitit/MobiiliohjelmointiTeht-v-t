package com.example.viikko2mobiili
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.viikko2mobiili.domain.Task
import com.example.viikko2mobiili.domain.mockList


class TaskViewModel : ViewModel() {
    var tasks by mutableStateOf(listOf<Task>())
        private set

    init {
        tasks = mockList
    }

    fun addTask(task: Task) {
        tasks = tasks + task
    }

    fun toggleDone(id: Int) {
        tasks = tasks.map {
            if (it.id == id) it.copy(done = !it.done)
            else it
        }
    }
    fun sortByPriority() {
        tasks = tasks.sortedBy { it.priority }
    }

    fun sortByDueDate() {
        tasks = tasks.sortedBy { it.dueDate }
    }

    fun filterDone() {
        tasks = tasks.filter { it.done }
    }
    fun removeTask(id: Int) {
        tasks = tasks.filter {it.id != id}
    }
}