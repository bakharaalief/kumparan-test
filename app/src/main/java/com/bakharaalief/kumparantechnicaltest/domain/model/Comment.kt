package com.bakharaalief.kumparantechnicaltest.domain.model

data class Comment(
    val name: String,
    val postId: Int,
    val id: Int,
    val body: String,
    val email: String
)
