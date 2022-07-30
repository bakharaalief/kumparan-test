package com.bakharaalief.kumparantechnicaltest.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bakharaalief.kumparantechnicaltest.MainDispatcherRule
import com.bakharaalief.kumparantechnicaltest.data.remote.retrofit.ApiService
import com.bakharaalief.kumparantechnicaltest.domain.repository.IAlbumRepository
import com.bakharaalief.kumparantechnicaltest.util.DataDummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AlbumRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var albumRepository: IAlbumRepository
    private val albumIdDummy = 1

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        apiService = FakeApiService()
        albumRepository = AlbumRepository(apiService)
    }

    @Test
    fun `when getAlbumPhoto Should Not Null`() = runTest {
        val expectedListPhoto = DataDummy.createListPhotoResponse()
        val actualListPhoto = apiService.getAlbumPhoto(albumIdDummy)
        Assert.assertNotNull(expectedListPhoto)
        Assert.assertEquals(expectedListPhoto.size, actualListPhoto.size)
    }
}