package com.bakharaalief.kumparantechnicaltest.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bakharaalief.kumparantechnicaltest.MainDispatcherRule
import com.bakharaalief.kumparantechnicaltest.data.remote.retrofit.ApiService
import com.bakharaalief.kumparantechnicaltest.domain.repository.IPostRepository
import com.bakharaalief.kumparantechnicaltest.util.DataDummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PostRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var postRepository: IPostRepository
    private val postIdDummy = 1

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        apiService = FakeApiService()
        postRepository = PostRepository(apiService)
    }

    @Test
    fun `when getPost Should Not Null`() = runTest {
        val expectedListPost = DataDummy.createListPostResponse()
        val actualListPost = apiService.getAllPosts()
        Assert.assertNotNull(actualListPost)
        Assert.assertEquals(expectedListPost.size, actualListPost.size)
    }

    @Test
    fun `when getPostComment Should Not Null`() = runTest {
        val expectedListComment = DataDummy.createListCommentResponse()
        val actualListComment = apiService.getPostComment(postIdDummy)
        Assert.assertNotNull(actualListComment)
        Assert.assertEquals(expectedListComment.size, actualListComment.size)
    }
}