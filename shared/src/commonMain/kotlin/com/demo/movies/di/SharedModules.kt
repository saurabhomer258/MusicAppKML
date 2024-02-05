package com.demo.movies.di

import com.demo.movies.data.remote.MovieService
import com.demo.movies.data.remote.RemoteDataSource
import com.demo.movies.data.repository.MovieRepositoryImpl
import com.demo.movies.domain.repository.MovieRepository
import com.demo.movies.domain.usecase.GetMovieUseCase
import com.demo.movies.domain.usecase.GetMoviesUseCase
import com.demo.movies.util.provideDispatcher
import org.koin.dsl.module

// Data-related Koin module
private val dataModule = module {
    factory { RemoteDataSource(get(), get()) }
    factory { MovieService() }
}

// Utility-related Koin module
private val utilityModule = module {
    factory { provideDispatcher() }
}

// Domain-related Koin module
private val domainModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    factory { GetMoviesUseCase() }
    factory { GetMovieUseCase() }
}

// List of shared Koin modules
private val sharedModules = listOf(domainModule, dataModule, utilityModule)

// Function to get the shared modules
fun getSharedModules() = sharedModules
