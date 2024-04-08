package com.example.test.common.view.model.factory

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

internal typealias ViewModelCreator<VM> = () -> VM

@Suppress("UNCHECKED_CAST")
class AssistedViewModelFactory<VM : ViewModel>(
    private val viewModelCreator: ViewModelCreator<VM>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = viewModelCreator()
        require(modelClass == viewModel::class.java)
        return viewModel as T
    }
}

inline fun <reified VM : ViewModel> Fragment.assistedViewModel(
    noinline creator: ViewModelCreator<VM>
): Lazy<VM> = viewModels(
    factoryProducer = { AssistedViewModelFactory(creator) }
)

inline fun <reified VM : ViewModel> ComponentActivity.assistedViewModel(
    noinline creator: ViewModelCreator<VM>
): Lazy<VM> = viewModels(
    factoryProducer = { AssistedViewModelFactory(creator) }
)