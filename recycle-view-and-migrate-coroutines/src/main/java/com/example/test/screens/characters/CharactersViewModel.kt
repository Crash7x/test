package com.example.test.screens.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.test.common.domain.model.character.Character
import com.example.test.common.extensions.createMutableSingleEventFlow
import com.example.test.common.extensions.launchOrError
import com.example.test.common.navigation.NavCommand
import com.example.test.common.observable.loader.LoaderFlow
import com.example.test.screens.characters.domain.usecase.GetCharactersUseCase
import com.example.test.screens.characters.navigation.CharactersRouter
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class CharactersViewModel @AssistedInject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val loaderFlow: LoaderFlow,
    private val charactersRouter: CharactersRouter
) : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters = _characters.asStateFlow()

    private val _navCommand = createMutableSingleEventFlow<NavCommand>()
    val navCommand get() = _navCommand.asSharedFlow()

    fun loadData() {
        launchOrError(error = {
            Log.d("fff","error ${it.localizedMessage}")
        }) {
            loaderFlow.tryEmit(true)
            val characters = getCharactersUseCase.getCharacters()
            _characters.value = characters
            loaderFlow.tryEmit(false)
        }
    }

    fun navigateCharacterDetail(id: Int) {
        _navCommand.tryEmit(charactersRouter.getCharacterDetail(id))
    }

    @AssistedFactory
    interface Factory {
        fun create(): CharactersViewModel
    }
}