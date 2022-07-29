package com.bakharaalief.kumparantechnicaltest.util

import com.bakharaalief.kumparantechnicaltest.data.remote.response.AlbumResponse
import com.bakharaalief.kumparantechnicaltest.data.remote.response.CommentResponse
import com.bakharaalief.kumparantechnicaltest.data.remote.response.PhotoResponse
import com.bakharaalief.kumparantechnicaltest.data.remote.response.PostResponse
import com.bakharaalief.kumparantechnicaltest.domain.model.Album
import com.bakharaalief.kumparantechnicaltest.domain.model.Comment
import com.bakharaalief.kumparantechnicaltest.domain.model.Photo
import com.bakharaalief.kumparantechnicaltest.domain.model.Post
import com.bakharaalief.kumparantechnicaltest.util.StringHelper.capitalized
import com.bakharaalief.kumparantechnicaltest.util.StringHelper.titleCase

object DataMapper {
    fun postResponseToModel(input: List<PostResponse>): List<Post> {
        return input.map {
            Post(
                it.userId,
                it.id,
                it.title.titleCase(),
                it.body.capitalized(),
            )
        }
    }

    fun commentResponseToModel(input: List<CommentResponse>): List<Comment> {
        return input.map {
            Comment(
                it.name.titleCase(),
                it.postId,
                it.id,
                it.body,
                it.email
            )
        }
    }

    fun albumResponseToModel(input: List<AlbumResponse>): List<Album> {
        return input.map {
            Album(
                it.userId,
                it.id,
                it.title
            )
        }
    }

    fun photoResponseToModel(input: List<PhotoResponse>): List<Photo> {
        return input.map {
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