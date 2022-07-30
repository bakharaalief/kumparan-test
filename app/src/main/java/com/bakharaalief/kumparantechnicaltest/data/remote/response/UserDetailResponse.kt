package com.bakharaalief.kumparantechnicaltest.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(

    @field:SerializedName("website")
    val website: String,

    @field:SerializedName("address")
    val address: AddressResponse,

    @field:SerializedName("phone")
    val phone: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("company")
    val company: CompanyResponse,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("username")
    val username: String
)

data class AddressResponse(

    @field:SerializedName("zipcode")
    val zipcode: String,

    @field:SerializedName("geo")
    val geo: GeoResponse,

    @field:SerializedName("suite")
    val suite: String,

    @field:SerializedName("city")
    val city: String,

    @field:SerializedName("street")
    val street: String
)

data class GeoResponse(

    @field:SerializedName("lng")
    val lng: String,

    @field:SerializedName("lat")
    val lat: String
)

data class CompanyResponse(

    @field:SerializedName("bs")
    val bs: String,

    @field:SerializedName("catchPhrase")
    val catchPhrase: String,

    @field:SerializedName("name")
    val name: String
)
