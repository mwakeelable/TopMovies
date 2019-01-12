package com.wakeel.topmovies.di

import com.wakeel.topmovies.data.TopMoviesApi
import com.wakeel.topmovies.data.TopMoviesDownloader
import com.wakeel.topmovies.data.TopMoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providesTopMoviesRepository(topMoviesApi: TopMoviesApi): TopMoviesRepository =
        TopMoviesDownloader(topMoviesApi)
}