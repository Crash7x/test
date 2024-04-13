package com.example.test.screens.detail.character.domain.usecase

import android.net.Uri
import com.example.test.common.data.network.character.CharactersRepository
import com.example.test.common.data.network.episode.EpisodeRepository
import com.example.test.common.data.network.location.LocationRepository
import com.example.test.screens.detail.character.domain.model.CharacterDetail
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.rx2.await
import javax.inject.Inject

class GetCharacterDetailUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val locationRepository: LocationRepository,
    private val episodeRepository: EpisodeRepository
) : GetCharacterDetailUseCase {
    override suspend fun getCharacterDetail(id: Int): CharacterDetail {
        val character = charactersRepository.getSingleCharacter(id).subscribeOn(Schedulers.io()).await()
        val episodeIds = character.episode.mapNotNull { Uri.parse(it).lastPathSegment?.toIntOrNull() }
        val locationId = Uri.parse(character.characterLocation.url).lastPathSegment?.toIntOrNull()
        val location = locationId?.let { locationRepository.getLocation(it) }
        val episode = episodeRepository.getEpisodeIds(episodeIds)
        return CharacterDetail(
            character,
            listOfNotNull(location),
            episode
        )
    }
}