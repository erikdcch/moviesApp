package com.arctouch.moviesapp

import android.app.Application
import com.arctouch.moviesapp.di.AppComponent
import com.arctouch.moviesapp.di.AppModule
import com.arctouch.moviesapp.di.DaggerAppComponent

/**
 * Created by erikcruz on 11/11/18
 */
class AndroidApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)
}