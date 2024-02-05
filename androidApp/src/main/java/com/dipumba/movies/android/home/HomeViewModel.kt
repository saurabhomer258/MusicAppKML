package com.demo.movies.android.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.movies.domain.model.Movie
import com.demo.movies.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    // Mutable state variable for UI state management
    var uiState by mutableStateOf(HomeScreenState())
    private var currentPage = 1

    // Initialization block to load movies when the ViewModel is created
    init {
        loadMovies(forceReload = false)
    }

    // Function to load movies with optional force reload parameter
    fun loadMovies(forceReload: Boolean = false) {
        // Return if already loading
        if (uiState.loading) return
        // Reset page if force reload is true
        if (forceReload) currentPage = 1
        // Set refreshing state when loading the first page
        if (currentPage == 1) uiState = uiState.copy(refreshing = true)

        viewModelScope.launch {
            uiState = uiState.copy(
                loading = true
            )

            try {
                // Fetch movies from the use case
                val resultMovies = getMoviesUseCase(page = currentPage)
                val movies = if (currentPage == 1) resultMovies else uiState.movies + resultMovies

                // Increment page and update UI state
                currentPage += 1
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = resultMovies.isEmpty(),
                    movies = movies
                )

            } catch (error: Throwable) {
                // Handle error and update UI state with an error message
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = true,
                    errorMessage = "Could not load movies: ${error.localizedMessage}"
                )
            }
        }
    }
}

data class HomeScreenState(
    var loading: Boolean = false,
    var refreshing: Boolean = false,
    var movies: List<Movie> = listOf(),
    var errorMessage: String? = null,
    var loadFinished: Boolean = false
)
