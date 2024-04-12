package com.example.test.screens.detail.character

import androidx.lifecycle.ViewModel
import com.example.test.common.extensions.launchOrError
import com.example.test.common.observable.loader.LoaderFlow
import com.example.test.screens.detail.character.domain.model.CharacterDetail
import com.example.test.screens.detail.character.domain.usecase.GetCharacterDetailUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull

class CharactersDetailViewModel @AssistedInject constructor(
    @Assisted private val characterId: Int,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val loaderFlow: LoaderFlow
) : ViewModel() {

    private val _characterDetail = MutableStateFlow<CharacterDetail?>(null)
    val characterDetail = _characterDetail.asStateFlow().filterNotNull()

    fun loadData() {
        launchOrError {
            loaderFlow.tryEmit(true)
            val characters = getCharacterDetailUseCase.getCharacterDetail(characterId)
            _characterDetail.value = characters
            loaderFlow.tryEmit(false)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(characterId: Int): CharactersDetailViewModel
    }
}