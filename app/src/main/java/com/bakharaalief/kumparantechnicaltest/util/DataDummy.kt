package com.bakharaalief.kumparantechnicaltest.util

import com.bakharaalief.kumparantechnicaltest.data.remote.response.*
import com.bakharaalief.kumparantechnicaltest.domain.model.*

object DataDummy {

    //dummy for response
    fun createListPostResponse(): List<PostResponse> {
        val data = ArrayList<PostResponse>()
        for (i in 1..10) {
            data.add(
                PostResponse(
                    i,
                    "Title $i",
                    "body $i",
                    1
                )
            )
        }
        return data
    }

    fun createUserResponse(): UserDetailResponse {
        val geo = GeoResponse(
            "-37.3159",
            "81.1496"
        )

        val address = AddressResponse(
            "92998-3874",
            geo,
            "Apt. 556",
            "Gwenborough",
            "Kulas Light"
        )

        val companyResponse = CompanyResponse(
            "harness real-time e-markets",
            "Multi-layered client-server neural-net",
            "Romaguera-Crona"
        )

        return UserDetailResponse(
            "hildegard.org",
            address,
            "1-770-736-8031 x56442",
            "Leanne Graham",
            companyResponse,
            1,
            "Sincere@april.biz",
            "Bret"
        )
    }

    fun createListAlbumResponse(): List<AlbumResponse> {
        val data = ArrayList<AlbumResponse>()
        for (i in 1..10) {
            data.add(
                AlbumResponse(
                    1,
                    "name $i",
                    1,
                )
            )
        }
        return data
    }

    fun createListCommentResponse(): List<CommentResponse> {
        val data = ArrayList<CommentResponse>()
        for (i in 1..10) {
            data.add(
                CommentResponse(
                    "name $i",
                    1,
                    i,
                    "body $i",
                    "email $i"
                )
            )
        }
        return data
    }

    fun createListPhotoResponse(): List<PhotoResponse> {
        val data = ArrayList<PhotoResponse>()
        for (i in 1..10) {
            data.add(
                PhotoResponse(
                    1,
                    i,
                    "title $i",
                    "https://via.placeholder.com/600/92c952",
                    "https://via.placeholder.com/150/92c952"
                )
            )
        }
        return data
    }

    //dummy for data
    fun createListPostData(): List<Post> {
        return createListPostResponse().map {
            Post(
                it.userId,
                it.id,
                it.title,
                it.body
            )
        }
    }

    fun createUserData(): User {
        val geo = Geo(
            "10",
            "11",
        )

        val address = Address(
            "zipcode test",
            geo,
            "suit test",
            "city test",
            "street test"
        )

        return User(
            1,
            "name test",
            "username test",
            "email test",
            address,
            "company test"
        )
    }

    fun createListAlbumDummy(): List<Album> {
        return createListAlbumResponse().map {
            Album(
                it.userId,
                it.id,
                it.title
            )
        }
    }

    fun createListCommentDummy(): List<Comment> {
        return createListCommentResponse().map {
            Comment(
                it.name,
                it.postId,
                it.id,
                it.body,
                it.email
            )
        }
    }

    fun createListPhotoDummy(): List<Photo> {
        return createListPhotoResponse().map {
            Photo(
                it.albumId,
                it.id,
                it.title,
                it.url,
                it.thumbnailUrl
            )
        }
    }
}