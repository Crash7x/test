package com.example.test.common.application.di

import android.app.Application
import com.example.test.common.application.TestApplication
import com.example.test.common.data.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    fun getRetrofit(): Retrofit

    fun inject(app: TestApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}