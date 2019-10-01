package com.amin.sample.ui.posts

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.amin.sample.base.BaseViewModel
import com.amin.sample.model.Post
import com.amin.sample.network.PostApi
import com.amin.sample.utils.LDR
import com.amin.sample.utils.androidThread
import com.amin.sample.utils.ioThread
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class PostsViewModel : BaseViewModel() {

    @Inject
    lateinit var postApi: PostApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val postLiveData: MutableLiveData<LDR<List<Post>>> = MutableLiveData()


    private lateinit var subscription: Disposable

    fun loadPosts() {
        postApi.getPosts()
            .subscribeOn(ioThread())
            .observeOn(androidThread())
            .subscribe(GetPostsConsumer())
    }

    inner class GetPostsConsumer : MaybeObserver<List<Post>> {
        override fun onSubscribe(d: Disposable) {
            postLiveData.postValue(LDR.loading())
            loadingVisibility.value = View.VISIBLE
        }

        override fun onError(e: Throwable) {
            postLiveData.postValue(LDR.error(e))
            loadingVisibility.value = View.GONE
        }

        override fun onSuccess(t: List<Post>) {
            postLiveData.postValue(LDR.success(t))
            loadingVisibility.value = View.GONE
        }

        override fun onComplete() {
            loadingVisibility.value = View.GONE
        }

    }
}