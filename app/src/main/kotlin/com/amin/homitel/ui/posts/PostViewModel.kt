package com.amin.homitel.ui.posts

import androidx.lifecycle.MutableLiveData
import com.amin.homitel.base.BaseViewModel
import com.amin.homitel.model.Post
import com.amin.homitel.utils.Constants

class PostViewModel : BaseViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()
    private var id: String = ""
    fun bind(post: Post) {
        postTitle.value = post.title
        postBody.value = post.body
        id = post.id.toString()
    }

    fun getPostTitle(): MutableLiveData<String> {
        return postTitle
    }

    fun getPostImage(): String {
        return Constants.IMAGE_RANDOM_URL + id + "/250"
    }

    fun getPostBody(): MutableLiveData<String> {
        return postBody
    }

}