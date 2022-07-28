package com.bakharaalief.kumparantechnicaltest.data.remote.retrofit

import com.bakharaalief.kumparantechnicaltest.data.remote.response.CommentResponse
import com.bakharaalief.kumparantechnicaltest.data.remote.response.PostResponse
import com.bakharaalief.kumparantechnicaltest.data.remote.response.UserDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    suspend fun getAllPosts(): List<PostResponse>

    @GET("users/{id}")
    suspend fun getUserDetail(@Path("id") id: Int): UserDetailResponse

    @GET("posts/{id}/comments")
    suspend fun getPostComment(@Path("id") id: Int): List<CommentResponse>
}