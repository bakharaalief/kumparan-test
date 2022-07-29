package com.bakharaalief.kumparantechnicaltest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bakharaalief.kumparantechnicaltest.data.remote.retrofit.ApiService
import com.bakharaalief.kumparantechnicaltest.domain.model.Photo
import com.bakharaalief.kumparantechnicaltest.domain.repository.IAlbumRepository
import com.bakharaalief.kumparantechnicaltest.util.DataMapper

class AlbumRepository(private val apiService: ApiService) : IAlbumRepository {

    override fun getAlbumPhoto(albumId: Int): LiveData<Result<List<Photo>>> = liveData {
        emit(Result.Loading)

        try {
            val listPhoto = apiService.getAlbumPhoto(albumId)
            emit(Result.Success(DataMapper.photoResponseToModel(listPhoto)))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: AlbumRepository? = null

        fun getInstance(apiService: ApiService): AlbumRepository =
            instance ?: synchronized(this) {
                instance ?: AlbumRepository(apiService)
            }.also { instance = it }
    }
}