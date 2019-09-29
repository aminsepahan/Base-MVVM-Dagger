package com.amin.sample.ui.posts

import androidx.lifecycle.MutableLiveData
import com.amin.sample.base.BaseViewModel
import com.amin.sample.base.Configs
import com.amin.sample.model.Post

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
        return Configs.IMAGE_RANDOM_URL + id + "/250"
    }

    fun getPostBody(): MutableLiveData<String> {
        return postBody
    }

}