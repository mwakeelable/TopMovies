package com.wakeel.topmovies.presentation.movieslist

import com.wakeel.topmovies.domain.MoviesViewModel

sealed class MoviesListState {
    abstract val pageNum: Int
    abstract val loadedAllItems: Boolean
    abstract val data: List<MoviesViewModel>
}

data class DefaultState(
    override val pageNum: Int,
    override val loadedAllItems: Boolean,
    override val data: List<MoviesViewModel>
) : MoviesListState()

data class LoadingState(
    override val pageNum: Int,
    override val loadedAllItems: Boolean,
    override val data: List<MoviesViewModel>
) : MoviesListState()

data class PaginatingState(
    override val pageNum: Int,
    override val loadedAllItems: Boolean,
    override val data: List<MoviesViewModel>
) : MoviesListState()

data class ErrorState(
    val errorMessage: String,
    override val pageNum: Int,
    override val loadedAllItems: Boolean,
    override val data: List<MoviesViewModel>
) : MoviesListState()