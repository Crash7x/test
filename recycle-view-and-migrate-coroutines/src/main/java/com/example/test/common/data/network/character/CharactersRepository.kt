package com.example.test.common.data.network.character

import com.example.test.common.domain.model.character.Character
import io.reactivex.Single

interface CharactersRepository {
    fun getCharacters(): Single<List<Character>>

    fun getSingleCharacter(id: Int): Single<Character>

    fun getCharacterIds(id: List<Int>): Single<List<Character>>
}