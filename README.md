# Movies App

This Kotlin Multiplatform project demonstrates a simple Movies app structure, with shared code for different platforms.

## Structure

The project is organized into the following packages:

- `com.demo.movies.android`: Android-specific code.
- `com.demo.movies.common`: Shared code between Android and other platforms.
- `com.demo.movies.data`: Data-related code, including remote data sources and repositories.
- `com.demo.movies.domain`: Domain-related code, including use cases and repositories.
- `com.demo.movies.util`: Utility code.

## Modules

### Data Module

The `data` module contains implementations for fetching movie data from a remote source.

- `RemoteDataSource`: Handles fetching data from the remote source.
- `MovieService`: Represents the service for fetching movie-related data.

### Utility Module

The `util` module provides utility functions and resources.

- `provideDispatcher()`: Utility function to provide a dispatcher.

### Domain Module

The `domain` module contains the core business logic and use cases.

- `MovieRepository`: Interface defining methods for retrieving movie data.
- `MovieRepositoryImpl`: Implementation of `MovieRepository`.
- `GetMoviesUseCase`: Use case for fetching a list of movies.
- `GetMovieUseCase`: Use case for fetching a single movie by its identifier.

## Modules Integration

These modules are integrated using the Koin dependency injection library.

## How to Use

1. Clone the repository.
2. Open the project in your preferred IDE (Android Studio, IntelliJ IDEA, etc.).
3. Explore the shared code in the `common` package, platform-specific code in `android`, and domain/data code in their respective packages.
4. Check the Koin module configurations in the `di` package for dependency injection.

Feel free to customize and extend the code based on your project requirements.

## License

This project is licensed under the [MIT License](LICENSE).
