package com.bakharaalief.kumparantechnicaltest.di

import com.bakharaalief.kumparantechnicaltest.data.AlbumRepository
import com.bakharaalief.kumparantechnicaltest.data.PostRepository
import com.bakharaalief.kumparantechnicaltest.data.UserRepository
import com.bakharaalief.kumparantechnicaltest.data.remote.retrofit.ApiConfig
import com.bakharaalief.kumparantechnicaltest.domain.repository.IAlbumRepository
import com.bakharaalief.kumparantechnicaltest.domain.repository.IPostRepository
import com.bakharaalief.kumparantechnicaltest.domain.repository.IUserRepository
import com.bakharaalief.kumparantechnicaltest.domain.usecase.*

object Injection {

    private val apiService = ApiConfig.getApiService()

    private fun providePostRepository(): IPostRepository = PostRepository.getInstance(apiService)
    private fun provideUserRepository(): IUserRepository = UserRepository.getInstance(apiService)
    private fun provideAlbumRepository(): IAlbumRepository = AlbumRepository.getInstance(apiService)

    fun providePostUseCase(): PostUseCase = PostUseCaseImpl(providePostRepository())
    fun provideUserUseCase(): UserUseCase = UserUseCaseImpl(provideUserRepository())
    fun provideAlbumUseCase(): AlbumUseCase = AlbumUseCaseImpl(provideAlbumRepository())
}
