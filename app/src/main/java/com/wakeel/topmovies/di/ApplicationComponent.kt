package com.wakeel.topmovies.di

import com.wakeel.topmovies.TopMoviesApplication
import com.wakeel.topmovies.presentation.di.MainActivityModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        NetModule::class,
        RepositoryModule::class,
        UseCasesModule::class,
        ViewModelModule::class,
        MainActivityModule::class
    )
)
interface ApplicationComponent {
    fun inject(app: TopMoviesApplication)
}