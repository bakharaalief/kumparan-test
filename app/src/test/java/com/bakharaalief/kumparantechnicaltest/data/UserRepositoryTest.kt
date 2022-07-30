package com.bakharaalief.kumparantechnicaltest.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bakharaalief.kumparantechnicaltest.MainDispatcherRule
import com.bakharaalief.kumparantechnicaltest.data.remote.retrofit.ApiService
import com.bakharaalief.kumparantechnicaltest.domain.repository.IUserRepository
import com.bakharaalief.kumparantechnicaltest.util.DataDummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var userRepository: IUserRepository
    private val userIdDummy = 1

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        apiService = FakeApiService()
        userRepository = UserRepository(apiService)
    }

    @Test
    fun `when getUserDetail Should Not Null`() = runTest {
        val expectedUser = DataDummy.createUserResponse()
        val actualUser = apiService.getUserDetail(userIdDummy)
        Assert.assertNotNull(expectedUser)
        Assert.assertEquals(expectedUser.name, actualUser.name)
    }

    @Test
    fun `when getUserAlbum Should Not Null`() = runTest {
        val expectedListAlbum = DataDummy.createListAlbumResponse()
        val actualListAlbum = apiService.getUserAlbum(userIdDummy)
        Assert.assertNotNull(actualListAlbum)
        Assert.assertEquals(actualListAlbum.size, expectedListAlbum.size)
    }
}