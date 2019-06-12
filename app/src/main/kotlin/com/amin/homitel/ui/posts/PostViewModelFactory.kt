package com.amin.homitel.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PostViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
            return PostsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}