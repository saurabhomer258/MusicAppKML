package com.demo.movies.android.detail

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.demo.movies.android.R
import com.demo.movies.android.Red

/**
 * Composable function for displaying detailed information about a movie.
 *
 * @param modifier The modifier for styling the UI elements.
 * @param uiState The state representing the details of the movie to be displayed.
 */
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailScreenState
) {
    // The main container for the detail screen
    Box(
        contentAlignment = Alignment.Center
    ) {
        // Check if movie details are available in the state
        uiState.movie?.let { movie ->
            // Vertical layout for arranging UI elements
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.background)
            ) {
                // Asynchronously load and display the movie image
                AsyncImage(
                    model = movie.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(320.dp)
                )

                // Another vertical layout for additional details and actions
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(20.dp)
                ) {
                    // Display the movie title with specified styling
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = modifier.height(8.dp))

                    // Button for starting to watch the movie
                    Button(
                        onClick = {},
                        modifier = modifier.fillMaxWidth().height(46.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Red
                        ),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp
                        )
                    ) {
                        // Icon to represent playing the movie
                        Icon(
                            painter = painterResource(id = R.drawable.play_button),
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = modifier.width(8.dp))

                        // Text on the button
                        Text(text = "Start watching now", color = Color.White)
                    }

                    Spacer(modifier = modifier.height(16.dp))

                    // Display release date information
                    Text(
                        text = "Released in ${movie.releaseDate}".uppercase(),
                        style = MaterialTheme.typography.overline
                    )

                    Spacer(modifier = modifier.height(4.dp))

                    // Display the movie description
                    Text(text = movie.description, style = MaterialTheme.typography.body2)
                }
            }
        }

        // Display a loading indicator if data is still loading
        if (uiState.loading){
            Row(
                modifier = modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    color = Red
                )
            }
        }
    }
}
