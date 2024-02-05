package com.demo.movies.domain.repository

import com.demo.movies.domain.model.Movie

internal interface MovieRepository {
    suspend fun getMovies(page: Int): List<Movie>

    suspend fun getMovie(movieId: Int): Movie
}