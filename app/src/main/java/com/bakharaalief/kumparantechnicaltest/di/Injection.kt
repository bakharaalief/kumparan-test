package com.bakharaalief.kumparantechnicaltest.di

import com.bakharaalief.kumparantechnicaltest.data.PostRepository
import com.bakharaalief.kumparantechnicaltest.data.UserRepository
import com.bakharaalief.kumparantechnicaltest.data.remote.retrofit.ApiConfig
import com.bakharaalief.kumparantechnicaltest.domain.repository.IPostRepository
import com.bakharaalief.kumparantechnicaltest.domain.repository.IUserRepository
import com.bakharaalief.kumparantechnicaltest.domain.usecase.PostUseCase
import com.bakharaalief.kumparantechnicaltest.domain.usecase.PostUseCaseImpl
import com.bakharaalief.kumparantechnicaltest.domain.usecase.UserUseCase
import com.bakharaalief.kumparantechnicaltest.domain.usecase.UserUseCaseImpl

object Injection {

    private val apiService = ApiConfig.getApiService()

    private fun providePostRepository(): IPostRepository = PostRepository.getInstance(apiService)

    fun providePostUseCase(): PostUseCase = PostUseCaseImpl(providePostRepository())

    private fun provideUserRepository(): IUserRepository = UserRepository.getInstance(apiService)

    fun provideUserUseCase(): UserUseCase = UserUseCaseImpl(provideUserRepository())
}
