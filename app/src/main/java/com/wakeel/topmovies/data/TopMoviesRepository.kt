package com.wakeel.topmovies.data

import io.reactivex.Observable

interface TopMoviesRepository {
    fun getTopMoviesList() : Observable<MovieListResult>
}