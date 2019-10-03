package com.amin.sample.base

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.amin.sample.R
import com.amin.sample.utils.LDR
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable

class MyMaybeObserverImgur(
    private val loadingVisibility: MutableLiveData<Int>? = null,
    private val apiShutterStockData: MutableLiveData<LDR<BaseResponseImgur>> = MutableLiveData(),
    private val page: Int = 0
) : MaybeObserver<BaseResponseImgur> {

    override fun onComplete() {

    }

    override fun onSuccess(t: BaseResponseImgur) {
        loadingVisibility?.value = View.GONE
        if (t.success) {
            apiShutterStockData.postValue(LDR.success(t))
        } else {
            apiShutterStockData.postValue(LDR.error(App.res.getString(R.string.something_went_wrong)))
        }
    }

    override fun onError(e: Throwable) {
        loadingVisibility?.value = View.GONE
        apiShutterStockData.postValue(LDR.error(App.res.getString(R.string.something_went_wrong)))
    }

    override fun onSubscribe(d: Disposable) {
        if (page == 0) {
            loadingVisibility?.value = View.VISIBLE
        }
        apiShutterStockData.postValue(LDR.started())
    }

}