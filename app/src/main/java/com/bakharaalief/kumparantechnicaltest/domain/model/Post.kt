package com.bakharaalief.kumparantechnicaltest.domain.model

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val userName: String? = null,
    val userCompany: String? = null,
)
