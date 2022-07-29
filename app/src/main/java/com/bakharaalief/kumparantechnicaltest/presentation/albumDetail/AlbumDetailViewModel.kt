package com.bakharaalief.kumparantechnicaltest.presentation.albumDetail

import androidx.lifecycle.ViewModel
import com.bakharaalief.kumparantechnicaltest.domain.usecase.AlbumUseCase

class AlbumDetailViewModel(private val albumUseCase: AlbumUseCase) : ViewModel() {

    fun getAlbumPhoto(albumId: Int) = albumUseCase.getAlbumPhoto(albumId)
}