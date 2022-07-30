package com.bakharaalief.kumparantechnicaltest.presentation.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.bakharaalief.kumparantechnicaltest.data.Result
import com.bakharaalief.kumparantechnicaltest.domain.model.Album
import com.bakharaalief.kumparantechnicaltest.domain.usecase.UserUseCase
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
class UserViewModelTest {

    private lateinit var userViewModel: UserViewModel
    private val albumDummy = DataDummy.createListAlbumDummy()
    private val userIdDummy = 1

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userUseCase: UserUseCase

    @Before
    fun setUp() {
        userViewModel = UserViewModel(userUseCase)
    }

    @Test
    fun `when getUserAlbum should not null and return success`() {

        val expectedListAlbum = MutableLiveData<Result<List<Album>>>()
        expectedListAlbum.value = Result.Success(albumDummy)

        `when`(userUseCase.getUserAlbum(userIdDummy)).thenReturn(expectedListAlbum)
        val actualListAlbum = userViewModel.getUserAlbum(userIdDummy).getOrAwaitValue()

        Mockito.verify(userUseCase).getUserAlbum(userIdDummy)
        Assert.assertNotNull(actualListAlbum)
        Assert.assertTrue(actualListAlbum is Result.Success)
    }
}