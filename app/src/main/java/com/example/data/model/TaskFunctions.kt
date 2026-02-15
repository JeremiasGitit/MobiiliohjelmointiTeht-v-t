package com.example.data.model

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

