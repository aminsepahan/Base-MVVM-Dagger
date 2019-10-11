package com.amin.sample.ui.shutterStock

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.amin.sample.R
import com.amin.sample.base.App
import com.amin.sample.base.BaseResponseShutterStock
import com.amin.sample.utils.LDR
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable

class MyMaybeObserverShutter(
    private val loadingVisibility: MutableLiveData<Int>? = null,
    private val apiShutterStockData: MutableLiveData<LDR<BaseResponseShutterStock>> = MutableLiveData(),
    private val page: Int = 0
) : MaybeObserver<BaseResponseShutterStock> {

    override fun onComplete() {

    }

    override fun onSuccess(t: BaseResponseShutterStock) {
        loadingVisibility?.value = View.GONE
        apiShutterStockData.postValue(LDR.success(t))
    }

    override fun onError(e: Throwable) {
        loadingVisibility?.value = View.GONE
        apiShutterStockData.postValue(LDR.error(""))
    }

    override fun onSubscribe(d: Disposable) {
        if (page == 0) {
            loadingVisibility?.value = View.VISIBLE
        }
        apiShutterStockData.postValue(LDR.started())
    }

}