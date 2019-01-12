package com.wakeel.topmovies.domain

import com.wakeel.topmovies.data.Movie
import com.wakeel.topmovies.data.TopMoviesRepository
import io.reactivex.Observable

class MoviesListInteractor(private val topMoviesRepository: TopMoviesRepository) : MoviesListUseCases {
    override fun getMoviesListBy(): Observable<List<MoviesViewModel>> {
        return topMoviesRepository.getTopMoviesList().map { movies -> movies.movies.map(moviesViewModelMapper) }
    }

    val moviesViewModelMapper: (Movie) -> MoviesViewModel = { movie ->
        MoviesViewModel(movie.id, movie.title, movie.posterPath!!, movie.releaseDate)
    }
}