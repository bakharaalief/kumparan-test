package com.bakharaalief.kumparantechnicaltest.presentation.albumDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.bakharaalief.kumparantechnicaltest.data.Result
import com.bakharaalief.kumparantechnicaltest.domain.model.Photo
import com.bakharaalief.kumparantechnicaltest.domain.usecase.AlbumUseCase
import com.bakharaalief.kumparantechnicaltest.getOrAwaitValue
import com.bakharaalief.kumparantechnicaltest.util.DataDummy
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumDetailViewModelTest {

    private lateinit var albumDetailViewModel: AlbumDetailViewModel
    private val photoDummy = DataDummy.createListPhotoDummy()
    private val albumIdDummy = 1

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var albumUseCase: AlbumUseCase

    @Before
    fun setUp() {
        albumDetailViewModel = AlbumDetailViewModel(albumUseCase)
    }

    @Test
    fun `when getAlbumPhoto should not null and return success`() {

        val expectedListPhoto = MutableLiveData<Result<List<Photo>>>()
        expectedListPhoto.value = Result.Success(photoDummy)

        `when`(albumUseCase.getAlbumPhoto(albumIdDummy)).thenReturn(expectedListPhoto)
        val actualListPhoto = albumDetailViewModel.getAlbumPhoto(albumIdDummy).getOrAwaitValue()

        Mockito.verify(albumUseCase).getAlbumPhoto(albumIdDummy)
        Assert.assertNotNull(actualListPhoto)
        Assert.assertTrue(actualListPhoto is Result.Success)
    }
}