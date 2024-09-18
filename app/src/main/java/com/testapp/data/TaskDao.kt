package com.testapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for managing Task entities in the Room database.
 *
 * This interface defines methods for accessing and manipulating Task data.
 */
@Dao
interface TaskDao {

    /**
     * Retrieves all Task entities from the database.
     *
     * This method returns a flow of a list of tasks, allowing for reactive updates
     * whenever the data changes in the database.
     *
     * @return A Flow that emits a list of all Task entities.
     */
    @Query("SELECT * FROM task")
    fun getAllTasks(): Flow<List<Task>>

    /**
     * Inserts a new Task entity into the database.
     *
     * This method is a suspend function, allowing it to be called from a coroutine
     * to perform the insertion without blocking the main thread.
     *
     * @param task The Task entity to be inserted into the database.
     */
    @Insert
    suspend fun insert(task: Task)
}
