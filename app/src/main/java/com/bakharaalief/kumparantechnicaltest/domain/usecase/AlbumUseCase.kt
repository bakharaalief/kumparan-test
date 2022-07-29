package com.bakharaalief.kumparantechnicaltest.domain.usecase

import androidx.lifecycle.LiveData
import com.bakharaalief.kumparantechnicaltest.data.Result
import com.bakharaalief.kumparantechnicaltest.domain.model.Photo

interface AlbumUseCase {

    fun getAlbumPhoto(albumId: Int): LiveData<Result<List<Photo>>>
}