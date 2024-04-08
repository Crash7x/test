package com.example.test.common.activities.main

import androidx.lifecycle.ViewModel
import com.example.test.common.observable.loader.LoaderFlow
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModel @AssistedInject constructor(
    loaderFlow: LoaderFlow
) : ViewModel() {

    private val _loader = loaderFlow
    val loader get() = _loader.asSharedFlow().onEach { _loader.resetReplayCache() }

    @AssistedFactory
    interface Factory {
        fun create(): MainViewModel
    }
}