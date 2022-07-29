package com.bakharaalief.kumparantechnicaltest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bakharaalief.kumparantechnicaltest.data.remote.retrofit.ApiService
import com.bakharaalief.kumparantechnicaltest.domain.model.Address
import com.bakharaalief.kumparantechnicaltest.domain.model.Album
import com.bakharaalief.kumparantechnicaltest.domain.model.Geo
import com.bakharaalief.kumparantechnicaltest.domain.model.User
import com.bakharaalief.kumparantechnicaltest.domain.repository.IUserRepository
import com.bakharaalief.kumparantechnicaltest.util.DataMapper

class UserRepository(private val apiService: ApiService) : IUserRepository {

    override fun getUserDetail(userId: Int): LiveData<Result<User>> = liveData {
        emit(Result.Loading)

        try {
            val userDetail = apiService.getUserDetail(userId)

            val geo = Geo(
                userDetail.address.geo.lng,
                userDetail.address.geo.lat,
            )

            val address = Address(
                userDetail.address.zipcode,
                geo,
                userDetail.address.suite,
                userDetail.address.city,
                userDetail.address.street
            )

            val user = User(
                userDetail.id,
                userDetail.name,
                userDetail.username,
                userDetail.email,
                address,
                userDetail.company.name
            )

            emit(Result.Success(user))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun getUserAlbum(userId: Int): LiveData<Result<List<Album>>> = liveData {
        emit(Result.Loading)

        try {
            val listAlbum = apiService.getUserAlbum(userId)
            emit(Result.Success(DataMapper.albumResponseToModel(listAlbum)))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(apiService: ApiService): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService)
            }.also { instance = it }
    }
}