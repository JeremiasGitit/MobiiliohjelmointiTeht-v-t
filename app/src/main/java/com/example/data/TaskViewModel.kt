package com.example.data
//import com.example.data.model.Task

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

