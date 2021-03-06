package com.amin.sample.ui.imgur

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.amin.sample.base.BaseResponseImgur
import com.amin.sample.base.BaseViewModel
import com.amin.sample.network.ImgurApi
import com.amin.sample.utils.LDR
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ImgurViewModel : BaseViewModel() {

    @Inject
    lateinit var imgurApi: ImgurApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val apiLiveData: MutableLiveData<LDR<BaseResponseImgur>> = MutableLiveData()

    private var subscription: Disposable? = null
    @SuppressLint("DefaultLocale")
    fun loadImages(
        page: Int = 1,
        section: ImgurFragAdapter.SectionType,
        sort: String = "viral"
    ) = apiCall(
        imgurApi.getGalley(section.name.toLowerCase(), sort, page),
        MyMaybeObserverImgur(loadingVisibility, apiLiveData, page)
    )


    override fun onCleared() {
        super.onCleared()
        subscription?.dispose()
    }
}