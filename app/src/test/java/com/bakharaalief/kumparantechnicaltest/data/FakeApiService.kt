package com.bakharaalief.kumparantechnicaltest.data

import com.bakharaalief.kumparantechnicaltest.data.remote.response.*
import com.bakharaalief.kumparantechnicaltest.data.remote.retrofit.ApiService
import com.bakharaalief.kumparantechnicaltest.util.DataDummy

class FakeApiService : ApiService {

    override suspend fun getAllPosts(): List<PostResponse> {
        return DataDummy.createListPostResponse()
    }

    override suspend fun getUserDetail(id: Int): UserDetailResponse {
        return DataDummy.createUserResponse()
    }

    override suspend fun getUserAlbum(id: Int): List<AlbumResponse> {
        return DataDummy.createListAlbumResponse()
    }

    override suspend fun getPostComment(id: Int): List<CommentResponse> {
        return DataDummy.createListCommentResponse()
    }

    override suspend fun getAlbumPhoto(id: Int): List<PhotoResponse> {
        return DataDummy.createListPhotoResponse()
    }
}