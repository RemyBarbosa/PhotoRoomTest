package com.photoroomtest.application

import android.app.Application
import com.facebook.stetho.Stetho
import com.photoroomtest.application.di.applicationInjectionsModules
import org.koin.android.ext.android.startKoin

open class BaseApplication : Application() {

    open val koinApplicationInjectionsModules =
        applicationInjectionsModules
    override fun onCreate() {
        super.onCreate()

        startKoin(this, koinApplicationInjectionsModules)
        Stetho.initializeWithDefaults(this)
    }


}