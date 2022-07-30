package com.bakharaalief.kumparantechnicaltest.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.bakharaalief.kumparantechnicaltest.data.Result
import com.bakharaalief.kumparantechnicaltest.domain.model.Post
import com.bakharaalief.kumparantechnicaltest.domain.model.User
import com.bakharaalief.kumparantechnicaltest.domain.usecase.PostUseCase
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
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private val postDummy = DataDummy.createListPostData()
    private val userDummy = DataDummy.createUserData()
    private val userIdDummy = 1

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var postUseCase: PostUseCase

    @Mock
    private lateinit var userUseCase: UserUseCase

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(postUseCase, userUseCase)
    }

    @Test
    fun `when getAllPost should not null and return success`() {

        val expectedListPost = MutableLiveData<Result<List<Post>>>()
        expectedListPost.value = Result.Success(postDummy)

        `when`(postUseCase.getAllPost()).thenReturn(expectedListPost)
        val actualListPost = mainViewModel.getAllPost().getOrAwaitValue()

        Mockito.verify(postUseCase).getAllPost()
        Assert.assertNotNull(actualListPost)
        Assert.assertTrue(actualListPost is Result.Success)
    }

    @Test
    fun `when getUserDetail not null and return success`() {
        val expectedUser = MutableLiveData<Result<User>>()
        expectedUser.value = Result.Success(userDummy)

        `when`(userUseCase.getUserDetail(userIdDummy)).thenReturn(expectedUser)
        val actualUser = mainViewModel.getUserDetail(userIdDummy).getOrAwaitValue()

        Mockito.verify(userUseCase).getUserDetail(userIdDummy)
        Assert.assertNotNull(actualUser)
        Assert.assertTrue(actualUser is Result.Success)
    }
}