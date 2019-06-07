package com.amin.homitel.ui.posts

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.amin.homitel.base.BaseViewModel
import com.amin.homitel.model.Post
import com.amin.homitel.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel: BaseViewModel(){

    @Inject
    lateinit var postApi: PostApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val postListAdapter: PostListAdapter = PostListAdapter()

    private lateinit var subscription: Disposable

    init{
        loadPosts()
    }

    private fun loadPosts(){
        subscription = postApi.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                // Add result
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )
    }


    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList:List<Post>){
        postListAdapter.updatePostList(postList)
    }

    private fun onRetrievePostListError(){

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}