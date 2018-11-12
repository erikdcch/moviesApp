package com.arctouch.moviesapp.di

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by erikcruz on 11/11/18
 */
@Module
class ContextModule(val context: Context) {

    @Provides
    fun provideContext(): Context = context
}