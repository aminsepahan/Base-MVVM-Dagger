package com.amin.sample.base

import androidx.fragment.app.Fragment
import io.reactivex.disposables.Disposable

open class BaseFragment : Fragment() {

    var mainActivity: MainActivity? = null

    override fun onResume() {
        super.onResume()
        activity?.let {
            if (it is MainActivity) {
                mainActivity = it
                mainActivity?.currentFrag = this
            }
        }
    }

    var subscribe: Disposable? = null
        set(value) {
            field?.dispose()
            field = value
        }
}