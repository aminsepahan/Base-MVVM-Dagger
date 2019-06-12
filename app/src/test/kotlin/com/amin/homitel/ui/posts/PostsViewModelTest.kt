package com.amin.homitel.ui.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.amin.homitel.model.Post
import com.amin.homitel.network.PostApi
import com.amin.homitel.utils.LiveDataResult
import com.amin.homitel.utils.POST_MOCK_PATH
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.mock
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

        // Attacch fake observer

    }

    @Test
    fun `load post from Api`(){
        POST_MOCK_PATH = "posts.json"
//        val observer = mock(Observer::class.java) as Observer<LiveDataResult<List<Post>>>
//        this.postsViewModel.postLiveData.observeForever(observer)
        // Invoke
        this.postsViewModel.loadPosts()
        // Verify
        assertNotNull(this.postsViewModel.postLiveData.value)
        assertEquals(LiveDataResult.Status.SUCCESS, this.postsViewModel.postLiveData.value?.status)
    }


}