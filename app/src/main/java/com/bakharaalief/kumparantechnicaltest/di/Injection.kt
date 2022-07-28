package com.bakharaalief.kumparantechnicaltest.di

import com.bakharaalief.kumparantechnicaltest.data.remote.PostRepository
import com.bakharaalief.kumparantechnicaltest.data.remote.retrofit.ApiConfig
import com.bakharaalief.kumparantechnicaltest.domain.repository.IPostRepository
import com.bakharaalief.kumparantechnicaltest.domain.usecase.PostUseCase
import com.bakharaalief.kumparantechnicaltest.domain.usecase.PostUseCaseImplement

object Injection {

    private val apiService = ApiConfig.getApiService()

    private fun providePostRepository(): IPostRepository = PostRepository.getInstance(apiService)

    fun providePostUseCase(): PostUseCase = PostUseCaseImplement(providePostRepository())
}
