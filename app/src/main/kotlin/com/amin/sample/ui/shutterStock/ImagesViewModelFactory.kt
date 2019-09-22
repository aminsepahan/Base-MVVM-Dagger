package com.amin.sample.ui.shutterStock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ImagesViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(ImagesViewModel::class.java)) {
            return ImagesViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}