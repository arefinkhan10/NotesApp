package com.testapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testapp.data.Task
import com.testapp.data.TaskDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel class for managing Task data and operations.
 *
 * This class provides the logic to interact with the TaskDao and manages
 * the state of the task list, exposing it as a StateFlow.
 *
 * @property taskDao The DAO used for accessing and manipulating Task entities.
 */
class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {

    /**
     * A StateFlow that holds the list of tasks retrieved from the database.
     *
     * This flow emits the current list of tasks and updates when the data changes.
     */
    val tasks: StateFlow<List<Task>> = taskDao.getAllTasks()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList()) // State management for tasks

    /**
     * Saves a new task with the specified title.
     *
     * This function launches a coroutine to insert the new task into the database
     * without blocking the main thread.
     *
     * @param note The title of the task to be saved.
     */
    fun saveTask(note: String) {
        viewModelScope.launch {
            taskDao.insert(Task(title = note)) // Insert the task into the database
        }
    }
}

