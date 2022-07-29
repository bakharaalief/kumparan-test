package com.bakharaalief.kumparantechnicaltest.domain.usecase

import androidx.lifecycle.LiveData
import com.bakharaalief.kumparantechnicaltest.data.Result
import com.bakharaalief.kumparantechnicaltest.domain.model.Comment
import com.bakharaalief.kumparantechnicaltest.domain.model.Post
import com.bakharaalief.kumparantechnicaltest.domain.repository.IPostRepository

class PostUseCaseImpl(private val postRepository: IPostRepository) : PostUseCase {

    override fun getAllPost(): LiveData<Result<List<Post>>> = postRepository.getAllPost()

    override fun getPostComment(postId: Int): LiveData<Result<List<Comment>>> =
        postRepository.getPostComment(postId)
}