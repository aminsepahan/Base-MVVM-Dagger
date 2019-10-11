package com.amin.sample.ui.posts

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.amin.sample.base.BaseViewModel
import com.amin.sample.base.MyMaybeObserver
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

    fun loadPosts() {
        postApi.getPosts()
            .subscribeOn(ioThread())
            .observeOn(androidThread())
            .subscribe(MyMaybeObserver(loadingVisibility, postLiveData))
    }

}