package com.bakharaalief.kumparantechnicaltest.data.remote.response

import com.google.gson.annotations.SerializedName

data class AlbumResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("userId")
    val userId: Int
)
