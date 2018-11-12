package com.arctouch.moviesapp.di

import android.content.Context
import com.arctouch.moviesapp.AndroidApplication
import com.arctouch.moviesapp.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by erikcruz on 11/11/18
 */
@Module
class AppModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideMoviesRepository(dataSource: MoviesRepository.Remote): MoviesRepository = dataSource

}