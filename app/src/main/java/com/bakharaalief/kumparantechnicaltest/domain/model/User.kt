package com.bakharaalief.kumparantechnicaltest.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val company: String
) : Parcelable

@Parcelize
data class Address(
    val zipcode: String,
    val geo: Geo,
    val suite: String,
    val city: String,
    val street: String
) : Parcelable

@Parcelize
data class Geo(
    val lng: String,
    val lat: String
) : Parcelable