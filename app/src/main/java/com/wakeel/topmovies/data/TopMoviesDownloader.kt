package com.wakeel.topmovies.data

import io.reactivex.Observable

class TopMoviesDownloader(private val topMoviesApi: TopMoviesApi) : TopMoviesRepository {
    override fun getTopMoviesList(): Observable<MovieListResult> = topMoviesApi.getMoviesList()

}