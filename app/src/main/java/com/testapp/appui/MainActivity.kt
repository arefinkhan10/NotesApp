//noinspection UsingMaterialAndMaterial3Libraries
package com.testapp.appui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.testapp.data.db.AppDatabase
import com.testapp.viewmodel.TaskViewModel

/**
 * The main activity for the application that sets up the UI and navigation.
 *
 * This activity initializes the [TaskViewModel] and sets the content of the activity using Jetpack Compose.
 * It also defines the navigation host and its destinations.
 */
class MainActivity : ComponentActivity() {
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get an instance of the AppDatabase and the TaskDao
        val database = AppDatabase.getDatabase(this)
        val taskDao = database.taskDao()
        // Initialize the ViewModel with the TaskDao
        taskViewModel = TaskViewModel(taskDao)

        setContent {
            MyApp {
                val navController = rememberNavController() // Create a NavController for managing navigation
                NavHost(navController = navController, startDestination = "main") { // Set up the navigation host
                    composable("main") { MainScreen(taskViewModel, navController) } // Main screen route
                    composable("addNote") { AddNoteScreen(taskViewModel, navController) } // Add note screen route
                }
            }
        }
    }
}

/**
 * A composable function that wraps content in a Material theme.
 *
 * This function provides a consistent Material design theme for the app's UI.
 *
 * @param content The composable content to be displayed within the Material theme.
 */
@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme {
        content() // Display the provided content
    }
}

/**
 * A composable function that represents the main screen of the application.
 *
 * This screen displays a list of tasks and includes a top app bar and a floating action button
 * to navigate to the add note screen.
 *
 * @param taskViewModel The [TaskViewModel] instance that provides task data.
 * @param navController The [NavController] used for navigating between screens.
 */
@Composable
fun MainScreen(taskViewModel: TaskViewModel, navController: NavController) {
    // Collect the list of tasks from the ViewModel state
    val tasks by taskViewModel.tasks.collectAsState()

    // Scaffold layout that provides the structure for the app's UI
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Tasks List") }) // Top app bar with title
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addNote") }) { // Navigate to add note screen
                Icon(Icons.Filled.Add, contentDescription = "Add Note") // Floating action button icon
            }
        }
    ) { innerPadding -> // Content padding provided by the Scaffold
        LazyColumn(contentPadding = innerPadding) { // Lazy column for displaying tasks
            items(tasks) { task -> // Iterate over the list of tasks
                TaskCard(task.title) // Display each task in a card
            }
        }
    }
}
