package com.wakeel.topmovies.presentation.movieslist

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Subcomponent
interface MoviesListFragmentSubcomponent : AndroidInjector<MoviesListFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MoviesListFragment>()
}

@Module(subcomponents = arrayOf(MoviesListFragmentSubcomponent::class))
abstract class MoviesListFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(MoviesListFragment::class)
    abstract fun bindMoviesListFragmentInjectorFactory(builder: MoviesListFragmentSubcomponent.Builder):
            AndroidInjector.Factory<out Fragment>
}