package com.wakeel.topmovies.di

import android.arch.lifecycle.ViewModel
import com.wakeel.topmovies.presentation.movieslist.MoviesListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesListViewModel::class)
    abstract fun bindMoviesListViewModel(viewModel: MoviesListViewModel) : ViewModel
}