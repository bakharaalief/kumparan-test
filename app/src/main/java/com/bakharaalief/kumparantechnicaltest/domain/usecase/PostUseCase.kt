package com.bakharaalief.kumparantechnicaltest.domain.usecase

import androidx.lifecycle.LiveData
import com.bakharaalief.kumparantechnicaltest.data.remote.Result
import com.bakharaalief.kumparantechnicaltest.domain.model.Comment
import com.bakharaalief.kumparantechnicaltest.domain.model.Post
import com.bakharaalief.kumparantechnicaltest.domain.model.User

interface PostUseCase {

    fun getAllPost(): LiveData<Result<List<Post>>>

    fun getUserDetail(userId: Int): LiveData<Result<User>>

    fun getPostComment(postId: Int): LiveData<Result<List<Comment>>>
}