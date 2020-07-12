package com.sth.quote.application

import android.app.Application
import com.sth.quote.di.KoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ApplicationClass)
            androidLogger(Level.INFO)
            modules(KoinModule)
        }
    }

}