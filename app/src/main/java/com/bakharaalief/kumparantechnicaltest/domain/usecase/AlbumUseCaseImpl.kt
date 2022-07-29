package com.bakharaalief.kumparantechnicaltest.domain.usecase

import androidx.lifecycle.LiveData
import com.bakharaalief.kumparantechnicaltest.data.Result
import com.bakharaalief.kumparantechnicaltest.domain.model.Photo
import com.bakharaalief.kumparantechnicaltest.domain.repository.IAlbumRepository

class AlbumUseCaseImpl(private val albumRepository: IAlbumRepository) : AlbumUseCase {

    override fun getAlbumPhoto(albumId: Int): LiveData<Result<List<Photo>>> =
        albumRepository.getAlbumPhoto(albumId)
}