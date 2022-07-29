package com.bakharaalief.kumparantechnicaltest.presentation.user

import androidx.lifecycle.ViewModel
import com.bakharaalief.kumparantechnicaltest.domain.usecase.UserUseCase

class UserViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun getUserAlbum(userId: Int) = userUseCase.getUserAlbum(userId)
}