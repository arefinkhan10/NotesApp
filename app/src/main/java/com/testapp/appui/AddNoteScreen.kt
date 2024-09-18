package com.testapp.appui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.testapp.viewmodel.TaskViewModel

/**
 * Composable function that represents the screen for adding a new note.
 *
 * This screen contains a text field for entering the note and a button to save the note.
 * Once the note is saved, the screen navigates back to the previous screen.
 *
 * @param viewModel The [TaskViewModel] instance that provides the functionality to save the note.
 * @param navController The [NavController] used for navigating between screens in the app.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(viewModel: TaskViewModel, navController: NavController) {
    // State variable to hold the text input for the note
    var noteText by remember { mutableStateOf("") }

    // Column layout to arrange the TextField and Button vertically
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxHeight()) { // Fill the available height

        // Box with rounded corners for the TextField
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = MaterialTheme.shapes.medium) // Background color and rounded corners
                .padding(4.dp) // Inner padding
        ) {
            // TextField for entering the note without the underline
            TextField(
                value = noteText,
                onValueChange = { noteText = it }, // Update noteText when the user types
                label = { Text("Enter Note") }, // Label for the text field
                modifier = Modifier.fillMaxWidth().height(250.dp), // Make the TextField take the full width
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent, // Hide the focused indicator
                    unfocusedIndicatorColor = Color.Transparent // Hide the unfocused indicator
                ),
                shape = MaterialTheme.shapes.medium, // Rounded corners for the text field
                singleLine = false // Make it single line for a cleaner look
            )
        }

        // Spacer to provide vertical space between the TextField and Button
        Spacer(modifier = Modifier.weight(1.0f))

        // Button to save the note, full width matching the parent
        Button(
            onClick = {
                viewModel.saveTask(noteText) // Save the note using the ViewModel
                navController.popBackStack() // Navigate back to the previous screen
            },
            modifier = Modifier.fillMaxWidth() // Full width button
        ) {
            Text("Save Note") // Button text
        }
    }
}


