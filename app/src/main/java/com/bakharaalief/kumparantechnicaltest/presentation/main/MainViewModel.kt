package com.bakharaalief.kumparantechnicaltest.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import com.bakharaalief.kumparantechnicaltest.domain.usecase.PostUseCase

class MainViewModel(private val postUseCase: PostUseCase) : ViewModel() {

    fun getAllPost() = postUseCase.getAllPost()
}