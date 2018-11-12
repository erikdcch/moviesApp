package com.arctouch.moviesapp.di

import com.arctouch.moviesapp.AndroidApplication
import com.arctouch.moviesapp.ui.movies.MoviesFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by erikcruz on 11/11/18
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(application: AndroidApplication)

    fun inject(moviesFragment: MoviesFragment)
}