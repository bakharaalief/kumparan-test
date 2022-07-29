package com.bakharaalief.kumparantechnicaltest.domain.repository

import androidx.lifecycle.LiveData
import com.bakharaalief.kumparantechnicaltest.data.Result
import com.bakharaalief.kumparantechnicaltest.domain.model.Photo

interface IAlbumRepository {
    fun getAlbumPhoto(albumId: Int): LiveData<Result<List<Photo>>>
}