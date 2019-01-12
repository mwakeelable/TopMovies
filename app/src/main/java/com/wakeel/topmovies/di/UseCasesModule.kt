package com.wakeel.topmovies.di

import com.wakeel.topmovies.data.TopMoviesRepository
import com.wakeel.topmovies.domain.MoviesListInteractor
import com.wakeel.topmovies.domain.MoviesListUseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun providesMoviesListUseCases(topMoviesRepository: TopMoviesRepository): MoviesListUseCases =
        MoviesListInteractor(topMoviesRepository)
}