package com.demo.movies.android

import android.app.Application
import com.demo.movies.android.di.appModule
import com.demo.movies.di.getSharedModules
import org.koin.core.context.startKoin

class Movie: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getSharedModules())
        }
    }
}