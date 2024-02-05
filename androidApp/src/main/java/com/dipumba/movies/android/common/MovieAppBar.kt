package com.demo.movies.android.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable function for a custom movie app bar.
 *
 * @param modifier Modifier for styling the app bar.
 * @param canNavigateBack Boolean indicating whether the app bar should display a back button.
 * @param currentScreen The current screen represented by a [Destination] object.
 * @param onNavigateBack Callback function to handle the back navigation action.
 */
@Composable
fun MovieAppBar(
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean,
    currentScreen: Destination,
    onNavigateBack: () -> Unit
) {
    // Surface representing the app bar with elevation
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = 4.dp
    ) {
        // Row layout for app bar content
        Row(
            modifier = modifier.padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back button with animated visibility based on canNavigateBack
            AnimatedVisibility(visible = canNavigateBack) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onBackground
                    )
                }
                Spacer(modifier = modifier.width(24.dp))
            }

            // Text displaying the title of the current screen
            Text(
                text = currentScreen.title,
                style = MaterialTheme.typography.h6,
                modifier = modifier.padding(12.dp),
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}
