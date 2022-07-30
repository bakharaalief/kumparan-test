package com.bakharaalief.kumparantechnicaltest.domain.usecase

import androidx.lifecycle.LiveData
import com.bakharaalief.kumparantechnicaltest.data.Result
import com.bakharaalief.kumparantechnicaltest.domain.model.Comment
import com.bakharaalief.kumparantechnicaltest.domain.model.Post

interface PostUseCase {

    fun getAllPost(): LiveData<Result<List<Post>>>

    fun getPostComment(postId: Int): LiveData<Result<List<Comment>>>
}