package com.amin.sample.ui.shutterStock

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.amin.sample.utils.LDR
import com.amin.sample.utils.MOCK_PATH
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
class ShutterStockViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ShutterStockViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.viewModel = ShutterStockViewModel()

    }

    @Test
    fun `load images from Api`() {
        MOCK_PATH = "shutterStock.json"

        this.viewModel.loadImages()

        assertNotNull(this.viewModel.apiLiveData.value)

        this.viewModel.apiLiveData.observeOnce { liveDataResult ->
            assertEquals(LDR.Status.SUCCESS, this.viewModel.apiLiveData.value?.status)
        }
    }

    @Test
    fun `load images from Api and go to Error state`() {
        MOCK_PATH = "error.json"

        this.viewModel.loadImages()

        assertNotNull(this.viewModel.apiLiveData.value)
        this.viewModel.apiLiveData.observeOnce { liveDataResult ->
           assertEquals(LDR.Status.ERROR, liveDataResult.status)
        }
    }

    @Test
    fun `number of images should be 10`() {
        MOCK_PATH = "shutterStock.json"

        this.viewModel.loadImages()

        assertNotNull(this.viewModel.apiLiveData.value)
        this.viewModel.apiLiveData.observeOnce { liveDataResult ->
            if (liveDataResult.status == LDR.Status.SUCCESS) assertEquals(liveDataResult.response?.data?.size, 10)
        }

    }
}