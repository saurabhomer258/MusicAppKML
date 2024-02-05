package com.demo.movies.android.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.movies.domain.model.Movie
import com.demo.movies.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the state and business logic of the movie detail screen.
 *
 * @param getMovieUseCase Use case for retrieving movie details.
 * @param movieId The ID of the movie for which details are to be displayed.
 */
class DetailViewModel(
    val getMovieUseCase: GetMovieUseCase,
    val movieId: Int
) : ViewModel() {
    // Mutable state property representing the current UI state
    var uiState by mutableStateOf(DetailScreenState())
        private set

    init {
        // Load movie details when the ViewModel is initialized
        loadMovie(movieId)
    }

    /**
     * Function to load movie details using the [getMovieUseCase].
     *
     * @param movieId The ID of the movie to be loaded.
     */
    private fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            // Set loading state to true
            uiState = uiState.copy(loading = true)

            try {
                // Attempt to fetch movie details
                val movie = getMovieUseCase(movieId = movieId)

                // Update UI state with successful result
                uiState = uiState.copy(loading = false, movie = movie)
            } catch (error: Throwable) {
                // Update UI state with error message if fetching fails
                uiState = uiState.copy(
                    loading = false,
                    errorMessage = "Could not load the movie"
                )
            }
        }
    }
}

/**
 * Data class representing the state of the movie detail screen.
 *
 * @property loading Boolean indicating whether data is currently being loaded.
 * @property movie Movie object containing details of the loaded movie.
 * @property errorMessage String containing an error message in case of a failure.
 */
data class DetailScreenState(
    var loading: Boolean = false,
    var movie: Movie? = null,
    var errorMessage: String? = null
)
