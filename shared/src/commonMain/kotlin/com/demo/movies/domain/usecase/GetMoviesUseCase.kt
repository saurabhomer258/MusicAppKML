package com.demo.movies.domain.usecase

import com.demo.movies.domain.model.Movie
import com.demo.movies.domain.repository.MovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMoviesUseCase : KoinComponent {
    // Injecting MovieRepository using Koin
    private val repository: MovieRepository by inject()

    /**
     * Invokes the use case to get a list of movies.
     *
     * @param page Page number for fetching movies.
     * @return List of movies.
     * @throws Exception if an error occurs during the operation.
     */
    @Throws(Exception::class)
    suspend operator fun invoke(page: Int): List<Movie> {
        return repository.getMovies(page = page)
    }
}