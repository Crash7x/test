package com.example.test.screens.detail.character.domain.model

import com.example.test.common.domain.model.character.Character
import com.example.test.common.domain.model.episode.Episode
import com.example.test.common.domain.model.location.Location

data class CharacterDetail(
    val character: Character,
    val location: List<Location>,
    val episode: List<Episode>,
)