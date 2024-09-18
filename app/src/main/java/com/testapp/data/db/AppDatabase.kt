package com.testapp.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.testapp.data.Task
import com.testapp.data.TaskDao

/**
 * Abstract class representing the Room database for the application.
 *
 * This database contains the [Task] entity and serves as the main access point
 * to the application's data. It provides a method to get the [TaskDao] for database operations.
 *
 * @property taskDao The DAO (Data Access Object) for performing operations on the Task entity.
 */
@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao // Abstract method to get the TaskDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null // Singleton instance of the database

        /**
         * Gets the singleton instance of the AppDatabase.
         *
         * This method ensures that only one instance of the database is created
         * and provides thread-safe access to it. If the instance is not already created,
         * it synchronizes the creation of the database instance.
         *
         * @param context The application context used to create the database instance.
         * @return The singleton instance of AppDatabase.
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) { // Synchronize to prevent multiple instances
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "notes_database" // Name of the database
                ).build()
                INSTANCE = instance // Assign the instance to the singleton variable
                instance // Return the created instance
            }
        }
    }
}

