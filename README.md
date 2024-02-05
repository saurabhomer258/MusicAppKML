# Movies App

## Overview
This is a Kotlin Multiplatform project for a Movie App, showcasing the use of Jetpack Compose for UI, Koin for dependency injection, and Clean Architecture principles.

## Key Technologies
- **Kotlin Multiplatform (KMP):**
  - Write shared code for Android, iOS, and other platforms.
  - Enable code reuse between different platforms.
  
- **Jetpack Compose:**
  - Modern Android UI toolkit for building native UIs using declarative syntax.
  - Used for creating the UI components in the Android app.
  
- **Koin:**
  - Lightweight dependency injection framework for Kotlin.
  - Provides dependency injection for components across the project.
  
- **Clean Architecture:**
  - Architectural pattern emphasizing separation of concerns.
  - Layers: Presentation (Compose UI), Domain (Use Cases), Data (Repositories).
  - Promotes maintainability, testability, and scalability.
    
## Screenshot

[![Video thumbnail](https://github.com/saurabhomer258/MusicAppKML/assets/25095906/b6507bfe-3b9d-4ab3-8318-a315eb682d21)]([https://www.youtube.com/watch?v=your_video_id](https://github.com/saurabhomer258/MusicAppKML/assets/25095906/58e1ab4f-739a-4deb-835f-3dd5d8d81bb1))



![2](https://github.com/saurabhomer258/MusicAppKML/assets/25095906/d6ce5f4e-f643-45e2-ae42-1b06594a4d35)


![3](https://github.com/saurabhomer258/MusicAppKML/assets/25095906/6997353a-86e5-47f9-8c24-fb516167c8d4)


![4](https://github.com/saurabhomer258/MusicAppKML/assets/25095906/b6507bfe-3b9d-4ab3-8318-a315eb682d21)



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

