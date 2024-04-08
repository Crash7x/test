package com.example.test.common.application.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

typealias ApplicationContext = Context

@Module
class AppModule {

    @Singleton
    @Provides
    fun applicationContext(application: Application): ApplicationContext =
        application.applicationContext
}