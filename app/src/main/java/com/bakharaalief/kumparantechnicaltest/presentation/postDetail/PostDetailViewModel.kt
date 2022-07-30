package com.bakharaalief.kumparantechnicaltest.presentation.postDetail

import androidx.lifecycle.ViewModel
import com.bakharaalief.kumparantechnicaltest.domain.usecase.PostUseCase

class PostDetailViewModel(private val postUseCase: PostUseCase) : ViewModel() {

    fun postComment(postId: Int) = postUseCase.getPostComment(postId)
}