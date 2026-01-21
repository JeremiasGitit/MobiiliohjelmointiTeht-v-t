package com.example.kotlinharjoitus.domain2

fun addTask(list: List<Task>, newTask: Task): List<Task> {
    return list + newTask
}

fun sortTasksByPriority (list: List<Task>): List<Task> {
    return list.sortedBy {it.priority}
}

fun sortByDueDate (list: List<Task>): List<Task> {
    return list.sortedBy {it.dueDate}
}

fun filterByDone (list: List<Task>): List<Task> {
    return list.filter { it.done }
}

/*fun toggleDone (list: List<Task>): List<Task> {
    return list.map { task ->
        if (task.id == taskId) {
            task.copy(done = !task.done) // toggle done
        } else {
            task
        }
    }
}*/
