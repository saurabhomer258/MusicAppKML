package com.demo.movies.domain.usecase

import com.demo.movies.domain.model.Movie
import com.demo.movies.domain.repository.MovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class GetMovieUseCase : KoinComponent {
    // Injecting MovieRepository using Koin
    private val repository: MovieRepository by inject()

    /**
     * Invokes the use case to get a single movie by its identifier.
     *
     * @param movieId Identifier of the movie to fetch.
     * @return The requested movie.
     * @throws Exception if an error occurs during the operation.
     */
    @Throws(Exception::class)
    suspend operator fun invoke(movieId: Int): Movie {
        return repository.getMovie(movieId = movieId)
    }
}
