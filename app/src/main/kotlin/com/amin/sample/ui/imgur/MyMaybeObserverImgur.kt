package com.amin.sample.ui.imgur

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.amin.sample.R
import com.amin.sample.base.App
import com.amin.sample.base.BaseResponseImgur
import com.amin.sample.utils.LDR
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable

class MyMaybeObserverImgur(
    private val loadingVisibility: MutableLiveData<Int>? = null,
    private val apiImgurData: MutableLiveData<LDR<BaseResponseImgur>> = MutableLiveData(),
    private val page: Int = 0
) : MaybeObserver<BaseResponseImgur> {

    override fun onComplete() {

    }

    override fun onSuccess(t: BaseResponseImgur) {
        loadingVisibility?.value = View.GONE
        if (t.success) {
            apiImgurData.postValue(LDR.success(t))
        } else {
            apiImgurData.postValue(LDR.error(App.res.getString(R.string.something_went_wrong)))
        }
    }

    override fun onError(e: Throwable) {
        loadingVisibility?.value = View.GONE
        apiImgurData.postValue(LDR.error(App.res.getString(R.string.something_went_wrong)))
    }

    override fun onSubscribe(d: Disposable) {
        if (page == 0) {
            loadingVisibility?.value = View.VISIBLE
        }
        apiImgurData.postValue(LDR.started())
    }

}