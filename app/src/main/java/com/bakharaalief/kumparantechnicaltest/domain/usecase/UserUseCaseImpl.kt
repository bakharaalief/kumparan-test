package com.bakharaalief.kumparantechnicaltest.domain.usecase

import androidx.lifecycle.LiveData
import com.bakharaalief.kumparantechnicaltest.data.Result
import com.bakharaalief.kumparantechnicaltest.domain.model.Album
import com.bakharaalief.kumparantechnicaltest.domain.model.User
import com.bakharaalief.kumparantechnicaltest.domain.repository.IUserRepository

class UserUseCaseImpl(private val userRepository: IUserRepository) : UserUseCase {

    override fun getUserDetail(userId: Int): LiveData<Result<User>> =
        userRepository.getUserDetail(userId)

    override fun getUserAlbum(userId: Int): LiveData<Result<List<Album>>> =
        userRepository.getUserAlbum(userId)
}