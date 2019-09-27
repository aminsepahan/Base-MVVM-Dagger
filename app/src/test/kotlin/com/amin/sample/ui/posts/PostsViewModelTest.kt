package com.amin.sample.ui.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.amin.sample.utils.LDR
import com.amin.sample.utils.POST_MOCK_PATH
import com.amin.sample.utils.observeOnce
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class PostsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var postsViewModel: PostsViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.postsViewModel = PostsViewModel()

    }

    @Test
    fun `load post from Api`() {
        POST_MOCK_PATH = "posts.json"

        this.postsViewModel.loadPosts()

        assertNotNull(this.postsViewModel.postLiveData.value)
        assertEquals(LDR.Status.SUCCESS, this.postsViewModel.postLiveData.value?.status)
    }

    @Test
    fun `load post from Api and go to Error state`() {
        POST_MOCK_PATH = "empty.json"

        this.postsViewModel.loadPosts()

        assertNotNull(this.postsViewModel.postLiveData.value)
        assertEquals(LDR.Status.ERROR, this.postsViewModel.postLiveData.value?.status)
    }

    @Test
    fun `number of posts should be 100`() {
        POST_MOCK_PATH = "posts.json"

        this.postsViewModel.loadPosts()

        assertNotNull(this.postsViewModel.postLiveData.value)
        this.postsViewModel.postLiveData.observeOnce { liveDataResult ->
            if (liveDataResult.status == LDR.Status.SUCCESS) assertEquals(liveDataResult.response?.size, 100)
        }

    }
}