package com.bakharaalief.kumparantechnicaltest.domain.usecase

import androidx.lifecycle.LiveData
import com.bakharaalief.kumparantechnicaltest.data.remote.Result
import com.bakharaalief.kumparantechnicaltest.domain.model.Post
import com.bakharaalief.kumparantechnicaltest.domain.repository.IPostRepository

class PostUseCaseImplement(private val postRepository: IPostRepository) : PostUseCase {

    override fun getAllPost(): LiveData<Result<List<Post>>> = postRepository.getAllPost()
}