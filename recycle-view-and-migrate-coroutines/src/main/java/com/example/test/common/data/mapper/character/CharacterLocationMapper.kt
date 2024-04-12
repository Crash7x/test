package com.example.test.common.data.mapper.character

import com.example.test.common.data.model.character.CharacterLocationResponse
import com.example.test.common.domain.model.character.CharacterLocation
import com.example.test.common.utils.Mapper
import javax.inject.Inject

class CharacterLocationMapper @Inject constructor() : Mapper<CharacterLocationResponse, CharacterLocation> {

    override fun transform(data: CharacterLocationResponse): CharacterLocation =
        CharacterLocation(
            name = data.name,
            url = data.url
        )
}