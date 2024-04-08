package com.example.test.common.activities.main.di

import com.example.test.common.activities.main.MainActivity
import com.example.test.common.application.di.AppComponent
import dagger.Component

@MainActivityScope
@Component(
    dependencies = [AppComponent::class]
)
interface MainActivityComponent {
    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): MainActivityComponent
    }
}