package com.amin.homitel.ui.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.amin.homitel.utils.LiveDataResult
import com.amin.homitel.utils.POST_MOCK_PATH
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PostsViewModelTestRobolectric {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var postsViewModel: PostsViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.postsViewModel = PostsViewModel()
    }

    @Test
    fun `should not be null`() {
        assertNotNull(postsViewModel)
    }

    @Test
    fun `load post from Api`() {
        POST_MOCK_PATH = "posts.json"

        this.postsViewModel.loadPosts()

        assertNotNull(this.postsViewModel.postLiveData.value)
        assertEquals(LiveDataResult.Status.SUCCESS, this.postsViewModel.postLiveData.value?.status)
    }
}