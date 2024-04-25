package com.example.lily02.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lily02.data.Task

class TodoListViewModel : ViewModel() {


    val dummyTaskModels = mutableListOf(
        Task(id = 1, title = "买菜"),
        Task(id = 2, title = "做饭"),
        Task(id = 3, title = "洗衣服")
    )
    private val _tasks = MutableLiveData<MutableList<Task>>().apply { value = dummyTaskModels.toMutableList() }
    val tasks: LiveData<MutableList<Task>> = _tasks
    fun updateTask(taskId: Int, isChecked: Boolean) {
        val updatedTasks = _tasks.value?.map { task ->
            if (task.id == taskId) {
                task.copy(completed = isChecked)
            } else {
                task
            }
        }?.toMutableList()
        _tasks.value = updatedTasks
    }

    fun deleteTask(taskId: Int) {
        val updatedTasks = _tasks.value?.filter { it.id != taskId }?.toMutableList()
        _tasks.value = updatedTasks
    }

    fun addTask(title: String) {
        val newTask = Task(id = (_tasks.value?.size ?: 0) + 1, title = title)
        _tasks.value = _tasks.value?.plus(newTask)?.toMutableList()
    }
}