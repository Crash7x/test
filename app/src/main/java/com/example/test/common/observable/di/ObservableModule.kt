package com.example.test.common.observable.di

import com.example.test.common.application.di.ApplicationScope
import com.example.test.common.observable.toolbar.ToolbarFlow
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

@Module
class ObservableModule {

    @ApplicationScope
    @Provides
    fun provideToolbarFlow(): ToolbarFlow = MutableSharedFlow(1, 1, BufferOverflow.DROP_OLDEST)
}