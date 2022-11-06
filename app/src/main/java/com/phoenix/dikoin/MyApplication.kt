package com.phoenix.dikoin

import android.app.Application
import com.phoenix.dikoin.di_module.activityModule
import com.phoenix.dikoin.di_module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger() //optional
            androidContext(this@MyApplication) //if we need the application context
            modules(appModule, activityModule)
        }
    }

}