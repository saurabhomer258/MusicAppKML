package com.demo.movies.android.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.demo.movies.android.Red
import com.demo.movies.domain.model.Movie

/**
 * Composable function for displaying the home screen with a grid of movies.
 *
 * @param modifier Modifier for styling the home screen.
 * @param uiState The state representing the data and loading status.
 * @param loadNextMovies Callback to load more movies when scrolling reaches the end.
 * @param navigateToDetail Callback to navigate to the movie detail screen.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeScreenState,
    loadNextMovies: (Boolean) -> Unit,
    navigateToDetail: (Movie) -> Unit
) {
    // Pull-to-refresh state
    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.refreshing,
        onRefresh = { loadNextMovies(true) })

    // Main content layout
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .pullRefresh(state = pullRefreshState)
    ) {
        // LazyVerticalGrid for displaying movies in a grid layout
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(
                uiState.movies,
                key = { _, movie -> movie.id }
            ) { index, movie ->
                // MovieListItem is a Composable for displaying each movie item
                MovieListItem(movie = movie, onMovieClick = { navigateToDetail(movie) })

                // Load more movies when scrolling reaches the end
                if (index >= uiState.movies.size - 1 && !uiState.loading && !uiState.loadFinished) {
                    LaunchedEffect(key1 = Unit, block = { loadNextMovies(false) })
                }
            }

            // Loading indicator when loading more movies
            if (uiState.loading && uiState.movies.isNotEmpty()) {
                item(span = { GridItemSpan(2) }) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(
                            color = Red
                        )
                    }
                }
            }
        }

        // Pull-to-refresh indicator
        PullRefreshIndicator(
            refreshing = uiState.refreshing,
            state = pullRefreshState,
            modifier = modifier.align(Alignment.TopCenter)
        )
    }
}
