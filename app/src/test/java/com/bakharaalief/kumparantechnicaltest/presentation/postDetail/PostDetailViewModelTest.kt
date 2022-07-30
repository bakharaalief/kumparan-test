package com.bakharaalief.kumparantechnicaltest.presentation.postDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.bakharaalief.kumparantechnicaltest.data.Result
import com.bakharaalief.kumparantechnicaltest.domain.model.Comment
import com.bakharaalief.kumparantechnicaltest.domain.usecase.PostUseCase
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
class PostDetailViewModelTest {

    private lateinit var postDetailViewModel: PostDetailViewModel
    private val commentDummy = DataDummy.createListCommentDummy()
    private val postIdDummy = 1

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var postUseCase: PostUseCase

    @Before
    fun setUp() {
        postDetailViewModel = PostDetailViewModel(postUseCase)
    }

    @Test
    fun `when getPostComment should not null and return success`() {
        val expectedListComment = MutableLiveData<Result<List<Comment>>>()
        expectedListComment.value = Result.Success(commentDummy)

        `when`(postUseCase.getPostComment(postIdDummy)).thenReturn(expectedListComment)
        val actualListComment = postDetailViewModel.postComment(postIdDummy).getOrAwaitValue()

        Mockito.verify(postUseCase).getPostComment(postIdDummy)
        Assert.assertNotNull(actualListComment)
        Assert.assertTrue(actualListComment is Result.Success)
    }
}