package com.amin.sample.ui.shutterStock

import androidx.lifecycle.MutableLiveData
import com.amin.sample.base.BaseResponseShutterStock
import com.amin.sample.base.BaseViewModel
import com.amin.sample.network.ShutterStockApi
import com.amin.sample.utils.LDR
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ShutterStockViewModel : BaseViewModel() {

    @Inject
    lateinit var shutterStockApi: ShutterStockApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val apiLiveData: MutableLiveData<LDR<BaseResponseShutterStock>> = MutableLiveData()

    private var subscription: Disposable? = null
    var searchPhrase = ""

    fun loadImages(page: Int = 1) {
        apiCall(
            shutterStockApi.searchImages(page, searchPhrase),
            MyMaybeObserverShutter(loadingVisibility, apiLiveData)
        )
    }


    override fun onCleared() {
        super.onCleared()
        subscription?.dispose()
    }
}