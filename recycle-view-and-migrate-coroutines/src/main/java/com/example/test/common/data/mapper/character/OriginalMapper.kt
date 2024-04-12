package com.example.test.common.data.mapper.character

import com.example.test.common.data.model.character.OriginalResponse
import com.example.test.common.domain.model.character.Original
import com.example.test.common.utils.Mapper
import javax.inject.Inject

class OriginalMapper @Inject constructor() : Mapper<OriginalResponse, Original> {

    override fun transform(data: OriginalResponse): Original =
        Original(
            name = data.name,
            url = data.url
        )
}