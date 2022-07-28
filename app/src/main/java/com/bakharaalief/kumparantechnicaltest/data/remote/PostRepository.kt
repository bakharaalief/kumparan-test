package com.bakharaalief.kumparantechnicaltest.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bakharaalief.kumparantechnicaltest.data.remote.retrofit.ApiService
import com.bakharaalief.kumparantechnicaltest.domain.model.*
import com.bakharaalief.kumparantechnicaltest.domain.repository.IPostRepository
import com.bakharaalief.kumparantechnicaltest.util.DataMapper


class PostRepository(private val apiService: ApiService) : IPostRepository {

    override fun getAllPost(): LiveData<Result<List<Post>>> = liveData {
        emit(Result.Loading)

        try {
            val listPost = apiService.getAllPosts()
            emit(Result.Success(DataMapper.postResponseToModel(listPost)))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun getUserDetail(userId: Int): LiveData<Result<User>> = liveData {
        emit(Result.Loading)

        try {
            val userDetail = apiService.getUserDetail(userId)

            val geo = Geo(
                userDetail.address.geo.lng,
                userDetail.address.geo.lat,
            )

            val address = Address(
                userDetail.address.zipcode,
                geo,
                userDetail.address.suite,
                userDetail.address.city,
                userDetail.address.street
            )

            val user = User(
                userDetail.id,
                userDetail.name,
                userDetail.username,
                userDetail.email,
                address,
                userDetail.company.name
            )

            emit(Result.Success(user))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun getPostComment(postId: Int): LiveData<Result<List<Comment>>> = liveData {
        emit(Result.Loading)

        try {
            val listComment = apiService.getPostComment(postId)
            emit(Result.Success(DataMapper.commentResponseToModel(listComment)))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: PostRepository? = null

        fun getInstance(apiService: ApiService): PostRepository =
            instance ?: synchronized(this) {
                instance ?: PostRepository(apiService)
            }.also { instance = it }
    }
}