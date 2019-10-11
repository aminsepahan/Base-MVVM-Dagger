package com.amin.sample.base

import androidx.fragment.app.Fragment
import com.amin.sample.R
import com.amin.sample.utils.extensions.showDismissDialog
import com.amin.sample.utils.extensions.snack
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

    @Suppress("UNUSED_PARAMETER")
    fun onError(message: String?, action: () -> Unit = {}, snackOrAlert: Boolean = false) {
        if (message != null) {
            if (snackOrAlert) {
                snack(message)
            } else {
                activity?.showDismissDialog(message, getString(R.string.ok), okListener = {})
            }
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun onError(error: Throwable?, action: () -> Unit = {}) {
        if (error != null) {
            activity?.showDismissDialog(
                if (error.localizedMessage.isNullOrEmpty()) getString(R.string.something_went_wrong)
                else error.localizedMessage,
                getString(R.string.ok),
                okListener = {})
        } else {
            activity?.showDismissDialog(
                getString(R.string.something_went_wrong),
                getString(R.string.ok),
                okListener = {})
        }
    }
}