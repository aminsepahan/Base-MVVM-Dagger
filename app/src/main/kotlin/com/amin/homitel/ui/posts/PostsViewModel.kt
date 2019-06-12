package com.amin.homitel.ui.posts

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.amin.homitel.base.BaseViewModel
import com.amin.homitel.model.Post
import com.amin.homitel.network.PostApi
import com.amin.homitel.utils.LiveDataResult
import com.amin.homitel.utils.androidThread
import com.amin.homitel.utils.ioThread
import io.reactivex.MaybeObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.http.POST
import javax.inject.Inject

class PostsViewModel: BaseViewModel(){

    @Inject
    lateinit var postApi: PostApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val postLiveData: MutableLiveData<LiveDataResult<List<Post>>> = MutableLiveData()


    private lateinit var subscription: Disposable

    fun loadPosts(){
        postApi.getPosts()
            .subscribeOn(ioThread())
            .observeOn(androidThread())
            .subscribe(GetPostsConsumer())
    }

    inner class GetPostsConsumer : MaybeObserver<List<Post>> {
        override fun onSubscribe(d: Disposable) {
            postLiveData.postValue(LiveDataResult.loading())
            loadingVisibility.value = View.VISIBLE
        }

        override fun onError(e: Throwable) {
            postLiveData.postValue(LiveDataResult.error(e))
            loadingVisibility.value = View.GONE
        }

        override fun onSuccess(t: List<Post>) {
            postLiveData.postValue(LiveDataResult.success(t))
            loadingVisibility.value = View.GONE
        }

        override fun onComplete() {
            loadingVisibility.value = View.GONE
        }

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}