package com.bakharaalief.kumparantechnicaltest.domain.repository

import androidx.lifecycle.LiveData
import com.bakharaalief.kumparantechnicaltest.data.Result
import com.bakharaalief.kumparantechnicaltest.domain.model.User

interface IUserRepository {
    fun getUserDetail(userId: Int): LiveData<Result<User>>
}