package com.bakharaalief.kumparantechnicaltest.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val userId: Int,
    val id: Int,
    val title: String
) : Parcelable
