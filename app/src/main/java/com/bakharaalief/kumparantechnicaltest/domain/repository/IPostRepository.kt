package com.bakharaalief.kumparantechnicaltest.domain.repository

import androidx.lifecycle.LiveData
import com.bakharaalief.kumparantechnicaltest.data.remote.Result
import com.bakharaalief.kumparantechnicaltest.domain.model.Post

interface IPostRepository {

    fun getAllPost(): LiveData<Result<List<Post>>>
}