package com.amin.sample.base

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.amin.sample.utils.LDR
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable

class MyMaybeObserver<T>(
    val loadingVisibility: MutableLiveData<Int>,
    val apiDataObserver: MutableLiveData<LDR<T>> = MutableLiveData(),
    private val page: Int = 0
) : MaybeObserver<T> {

    override fun onComplete() {

    }

    override fun onSuccess(t: T) {
        loadingVisibility.value = View.GONE
        apiDataObserver.postValue(LDR.success(t))
    }

    override fun onError(e: Throwable) {
        loadingVisibility.value = View.GONE
        apiDataObserver.postValue(LDR.error(e))
    }

    override fun onSubscribe(d: Disposable) {
        loadingVisibility.value = View.VISIBLE
    }

}