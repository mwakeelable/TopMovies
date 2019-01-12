package com.wakeel.topmovies.presentation.movieslist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.wakeel.topmovies.di.SCHEDULER_IO
import com.wakeel.topmovies.di.SCHEDULER_MAIN_THREAD
import com.wakeel.topmovies.domain.MoviesListUseCases
import com.wakeel.topmovies.domain.MoviesViewModel
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

private val TAG = MoviesListViewModel::class.java.name

class MoviesListViewModel
@Inject constructor(
    private val moviesListUseCases: MoviesListUseCases, @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler, @Named(
        SCHEDULER_MAIN_THREAD
    ) val observeOnScheduler: Scheduler
) : ViewModel() {

    val stateLiveData = MutableLiveData<MoviesListState>()

    init {
        stateLiveData.value = DefaultState(0, false, emptyList())
    }

    fun updateMoviesList() {
        val pageNum = obtainCurrentPageNum()
        stateLiveData.value = if (pageNum == 0)
            LoadingState(pageNum, false, obtainCurrentData())
        else
            PaginatingState(pageNum, false, obtainCurrentData())
        getMoviesList()
    }

    fun resetMoviesList() {
        val pageNum = 0
        stateLiveData.value = LoadingState(pageNum, false, emptyList())
        updateMoviesList()
    }

    fun restoreMoviesList() {
        val pageNum = 1
        stateLiveData.value = DefaultState(pageNum, false, obtainCurrentData())
    }

    private fun getMoviesList() {
        moviesListUseCases.getMoviesListBy()
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onMoviesListReceived, this::onError)
    }

    private fun onMoviesListReceived(moviesList: List<MoviesViewModel>) {
        val currentMoviesList = obtainCurrentData().toMutableList()
        val currentPageNum = obtainCurrentPageNum() + 1
        val areAllItemsLoaded = moviesList.size < 30
        currentMoviesList.addAll(moviesList)
        stateLiveData.value = DefaultState(currentPageNum, areAllItemsLoaded, currentMoviesList)
    }

    private fun onError(error: Throwable) {
        val pageNum = stateLiveData.value?.pageNum ?: 0
        stateLiveData.value =
                ErrorState(error.message ?: "", pageNum, obtainCurrentLoadedAllItems(), obtainCurrentData())
    }

    private fun obtainCurrentPageNum() = stateLiveData.value?.pageNum ?: 0

    private fun obtainCurrentData() = stateLiveData.value?.data ?: emptyList()

    private fun obtainCurrentLoadedAllItems() = stateLiveData.value?.loadedAllItems ?: false

}