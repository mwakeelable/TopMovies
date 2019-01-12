package com.wakeel.topmovies.presentation.di

import com.wakeel.topmovies.presentation.movieslist.MainActivity
import com.wakeel.topmovies.presentation.movieslist.MoviesListFragmentModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(MoviesListFragmentModule::class))
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}