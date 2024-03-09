package com.example.test.common.activities.main

import androidx.lifecycle.ViewModel
import com.example.test.common.observable.toolbar.ToolbarFlow
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModel @AssistedInject constructor(
    toolbarFlow: ToolbarFlow
) : ViewModel() {

    private val _toolbarData = toolbarFlow
    val toolbarData get() = _toolbarData.asSharedFlow().onEach { _toolbarData.resetReplayCache() }

    @AssistedFactory
    interface Factory {
        fun create(): MainViewModel
    }
}