package com.example.test.common.application

import android.app.Application
import android.content.Context
import com.example.test.common.application.di.AppComponent
import com.example.test.common.application.di.DaggerAppComponent

class TestApplication: Application() {

    private lateinit var _appComponent: AppComponent
    val appComponent get() = _appComponent


    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
            .apply { inject(this@TestApplication) }
    }
}


val Context.appComponent: AppComponent
    get() = when (this) {
        is TestApplication -> appComponent
        else -> this.applicationContext.appComponent
    }