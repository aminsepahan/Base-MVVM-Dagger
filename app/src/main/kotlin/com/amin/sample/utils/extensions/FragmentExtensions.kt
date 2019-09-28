@file:Suppress("unused")

package com.amin.sample.utils.extensions

import android.app.Activity
import android.content.res.ColorStateList
import android.content.res.Resources
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.amin.sample.R
import com.google.android.material.snackbar.Snackbar


fun Fragment.showDismissDialog(
    message: String, okBtnText: String = getString(R.string.thanks),
    okListener: () -> Unit = {}
) {
    activity?.showMessageDialog(message, okBtnText, okListener, showNegativeBtn = false)
}

fun Fragment.showMessageDialog(
    message: String?, okBtnText: String = getString(R.string.yes),
    okListener: () -> Unit = {}, noBtnText: String = getString(R.string.no),
    noListener: () -> Unit = {},
    showNegativeBtn: Boolean = false
) {
    activity?.showMessageDialog(
        message,
        okBtnText,
        okListener,
        noBtnText,
        noListener,
        showNegativeBtn
    )
}

fun Fragment.hideKeyboard() {
    val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Fragment.dpToPixels(dp: Float): Int {
    return (dp * resources.displayMetrics.density).toInt()

}

fun Fragment.getColor(@ColorRes id: Int): ColorStateList {
    return ColorStateList.valueOf(ResourcesCompat.getColor(resources, id, theme()))
}

fun Fragment.toast(message: String): Toast? {
    return activity?.let {
        val toast = Toast.makeText(it, message, Toast.LENGTH_SHORT)
        toast.show()
        toast
    }
}

fun Fragment.snack(
    message: String, buttonTxt: Int = R.string.ok,
    action: () -> Unit = {}, indefinite: Boolean = false
): Snackbar? {
    val snackBar = (if (view?.findViewById<View>(R.id.rootLay) != null) {
        if (indefinite) {
            Snackbar.make(
                view!!.findViewById(R.id.rootLay),
                message,
                Snackbar.LENGTH_INDEFINITE
            )
        } else {
            Snackbar.make(view!!.findViewById(R.id.rootLay), message, Snackbar.LENGTH_LONG)
        }
    } else {
        if (activity != null) {
            if (indefinite) {
                Snackbar.make(
                    activity!!.window.decorView.rootView,
                    message,
                    Snackbar.LENGTH_INDEFINITE
                )
            } else {
                Snackbar.make(
                    activity!!.window.decorView.rootView,
                    message,
                    Snackbar.LENGTH_LONG
                )
            }
        } else {
            null
        }
    })
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        snackBar?.setAction(buttonTxt) { run(action) }
            ?.setActionTextColor(resources.getColor(R.color.themeYellow, activity!!.theme))
    } else {
        @Suppress("DEPRECATION")
        snackBar?.setAction(buttonTxt) { run(action) }?.setActionTextColor(resources.getColor(R.color.themeYellow))
    }
    snackBar?.show()
    return snackBar
}

fun Fragment.theme(): Resources.Theme? {
    return activity?.theme
}