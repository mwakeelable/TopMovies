package com.wakeel.topmovies.data

import com.google.gson.annotations.SerializedName

class MovieListResult {
    var page: Int = 0
    @SerializedName("results")
    lateinit var movies: List<Movie>
}