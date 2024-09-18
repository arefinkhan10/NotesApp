package com.testapp.appui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A composable function that displays a card containing a task title.
 *
 * This function creates a card with a specified task title, applying styles such as background color,
 * padding, and text color to enhance visibility.
 *
 * @param taskTitle The title of the task to be displayed within the card.
 */
@Composable
fun TaskCard(taskTitle: String) {
    // Card layout to display the task title with specific styles
    Card(
        modifier = Modifier
            .fillMaxWidth() // Make the card take the full width
            .padding(8.dp), // Add padding around the card
        colors = CardDefaults.cardColors(containerColor = Color.Yellow), // Set the background color of the card
        shape = MaterialTheme.shapes.medium // Define the shape of the card
    ) {
        // Box layout to provide additional padding around the text
        Box(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
            // Text component displaying the task title
            Text(
                text = taskTitle, // The text to be displayed
                fontSize = 14.sp, // Font size of the text
                color = Color.Red, // Text color
                modifier = Modifier.padding(4.dp) // Padding around the text
            )
        }
    }
}

