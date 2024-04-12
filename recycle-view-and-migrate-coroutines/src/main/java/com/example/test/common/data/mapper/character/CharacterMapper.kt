package com.example.test.common.data.mapper.character

import com.example.test.common.data.model.character.CharacterResponse
import com.example.test.common.domain.model.character.Character
import com.example.test.common.domain.model.character.Gender
import com.example.test.common.domain.model.character.Status
import com.example.test.common.utils.Mapper
import javax.inject.Inject

class CharacterMapper @Inject constructor(
    private val originalMapper: OriginalMapper,
    private val characterLocationMapper: CharacterLocationMapper
) : Mapper<CharacterResponse, Character> {

    override fun transform(data: CharacterResponse): Character =
        Character(
            id = data.id,
            name = data.name,
            status = Status.getStatus(data.status),
            species = data.species,
            type = data.type,
            gender = Gender.getGender(data.gender),
            origin = originalMapper.transform(data.origin),
            characterLocation = characterLocationMapper.transform(data.location),
            image = data.image,
            episode = data.episode,
            url = data.url
        )
}