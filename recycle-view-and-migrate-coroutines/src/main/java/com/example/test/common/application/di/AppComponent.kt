package com.example.test.common.application.di

import android.app.Application
import com.example.test.common.application.TestApplication
import com.example.test.common.data.di.NetworkModule
import com.example.test.common.observable.di.ObservableModule
import com.example.test.common.observable.loader.LoaderFlow
import com.example.test.common.observable.toolbar.ToolbarFlow
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@ApplicationScope
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ObservableModule::class
    ]
)
interface AppComponent {

    fun getRetrofit(): Retrofit

    fun getToolbarFlow(): ToolbarFlow

    fun getLoaderFlow(): LoaderFlow

    fun inject(app: TestApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}