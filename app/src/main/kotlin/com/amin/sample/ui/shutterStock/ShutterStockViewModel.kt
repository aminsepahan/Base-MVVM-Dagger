package com.amin.sample.ui.shutterStock

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.amin.sample.base.BaseResponseShutterStock
import com.amin.sample.base.BaseViewModel
import com.amin.sample.base.MyMaybeObserverShutter
import com.amin.sample.model.Post
import com.amin.sample.model.ShutterStockImage
import com.amin.sample.network.PostApi
import com.amin.sample.network.ShutterStockApi
import com.amin.sample.utils.LDR
import com.amin.sample.utils.androidThread
import com.amin.sample.utils.ioThread
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ShutterStockViewModel: BaseViewModel(){

    @Inject
    lateinit var shutterStockApi: ShutterStockApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val apiLiveData: MutableLiveData<LDR<BaseResponseShutterStock>> = MutableLiveData()

    private lateinit var subscription: Disposable

    fun loadImages(){
        shutterStockApi.searchImages()
            .subscribeOn(ioThread())
            .observeOn(androidThread())
            .subscribe(MyMaybeObserverShutter(loadingVisibility, apiLiveData))
    }


    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}