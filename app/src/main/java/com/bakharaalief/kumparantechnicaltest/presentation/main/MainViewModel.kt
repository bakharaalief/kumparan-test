package com.bakharaalief.kumparantechnicaltest.presentation.main

import androidx.lifecycle.ViewModel
import com.bakharaalief.kumparantechnicaltest.domain.usecase.PostUseCase
import com.bakharaalief.kumparantechnicaltest.domain.usecase.UserUseCase

class MainViewModel(private val postUseCase: PostUseCase, private val userUseCase: UserUseCase) :
    ViewModel() {

    fun getAllPost() = postUseCase.getAllPost()

    fun getUserDetail(userId: Int) = userUseCase.getUserDetail(userId)
}