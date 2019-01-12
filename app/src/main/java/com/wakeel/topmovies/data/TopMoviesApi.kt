package com.wakeel.topmovies.data

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TopMoviesApi {
    @GET("movie/popular")
    fun getMoviesList(@Query("api_key") apiKey: String = "d3f9f87e5cc0c9e1f3c980a933d7ced9"): Observable<MovieListResult>
}


data class Movie(
    @SerializedName("id") var id: Int = -1,
    @SerializedName("vote_count") var voteCount: Int = 0,
    @SerializedName("vote_average") var voteAverage: Double = 0.0,
    @SerializedName("adult") var adult: Boolean = false,
    @SerializedName("popularity") var popularity: Double = 0.0,
    @SerializedName("title") var title: String,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("original_language") var originalLanguage: String,
    @SerializedName("original_title") var originalTitle: String,
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("overview") var overview: String? = null
)
