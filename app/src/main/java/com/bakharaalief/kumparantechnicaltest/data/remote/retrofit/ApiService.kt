package com.bakharaalief.kumparantechnicaltest.data.remote.retrofit

import com.bakharaalief.kumparantechnicaltest.data.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    suspend fun getAllPosts(): List<PostResponse>

    @GET("users/{id}")
    suspend fun getUserDetail(@Path("id") id: Int): UserDetailResponse

    @GET("users/{id}/albums")
    suspend fun getUserAlbum(@Path("id") id: Int): List<AlbumResponse>

    @GET("posts/{id}/comments")
    suspend fun getPostComment(@Path("id") id: Int): List<CommentResponse>

    @GET("albums/{id}/photos")
    suspend fun getAlbumPhoto(@Path("id") id: Int): List<PhotoResponse>
}