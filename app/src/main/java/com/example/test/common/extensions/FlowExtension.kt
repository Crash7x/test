package com.example.test.common.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

inline fun ViewModel.launchOrError(
    context: CoroutineContext? = null,
    crossinline action: suspend () -> Unit
) = viewModelScope.launchOrError(context ?: viewModelScope.coroutineContext, Timber::e, action)

inline fun ViewModel.launchOrError(
    context: CoroutineContext? = null,
    crossinline error: (Throwable) -> Unit,
    crossinline action: suspend () -> Unit
) = viewModelScope.launchOrError(context ?: viewModelScope.coroutineContext, error, action)

inline fun CoroutineScope.launchOrError(
    context: CoroutineContext? = null,
    crossinline action: suspend () -> Unit
) = launchOrError(context ?: coroutineContext, Timber::e, action)

inline fun CoroutineScope.launchOrError(
    context: CoroutineContext? = null,
    crossinline error: (Throwable) -> Unit,
    crossinline action: suspend () -> Unit
) = launch(context ?: coroutineContext) {
    catch(error) {
        action.invoke()
    }
}

suspend inline fun catch(
    crossinline error: (Exception) -> Unit,
    crossinline action: suspend () -> Unit
) {
    try {
        action()
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        error(e)
    }
}

inline fun <T> Flow<T>.observe(
    lifecycleOwner: LifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline observer: (T) -> Unit
) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(state) {
            collect { value -> observer(value) }
        }
    }
}