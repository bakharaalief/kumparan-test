package com.bakharaalief.kumparantechnicaltest.domain.usecase

import androidx.lifecycle.LiveData
import com.bakharaalief.kumparantechnicaltest.data.remote.Result
import com.bakharaalief.kumparantechnicaltest.domain.model.Post

interface PostUseCase {

    fun getAllPost(): LiveData<Result<List<Post>>>
}