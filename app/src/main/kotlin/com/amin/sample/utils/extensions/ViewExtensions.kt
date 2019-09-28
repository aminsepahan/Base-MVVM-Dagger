@file:Suppress("unused")

package com.amin.leadme.utils.extensions

import android.animation.*
import android.app.Activity
import android.content.ContextWrapper
import android.content.res.ColorStateList
import android.view.*
import android.view.View.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textfield.TextInputLayout


/**
 * Created by Amin on 08/06/2018.
 *
 */

fun EditText.isEmpty(): Boolean {
    return text.isNullOrEmpty()
}

fun TextView.string(): String {
    return if (text.isNullOrEmpty()) {
        ""
    } else {
        text.toString()
    }
}

fun EditText.isNotValidateTelephone(): Boolean {
    return if (text.isNullOrEmpty()) {
        false
    } else {
        return !(text.length == 11 && text.startsWith("0") && !text.startsWith("09"))
    }
}

fun EditText.isNotValidateMobilePhone(): Boolean {
    return if (text.isNullOrEmpty()) {
        false
    } else {
        return !(text.length == 11 && text.startsWith("09"))
    }
}

fun TextInputLayout.resetError() {
    error = ""
    isErrorEnabled = false
}

fun View.disable(disableOrEnable: Boolean = true) {
    isEnabled = !disableOrEnable
}

fun View.enable(enableDisable: Boolean = true) {
    isEnabled = enableDisable
}

fun ViewGroup.resetErrors() {
    for (i in 0 until childCount) {
        when (val view = getChildAt(i)) {
            is TextInputLayout -> view.resetError()
            is ViewGroup -> view.resetErrors()
        }
    }
}

fun View.isVisible(): Boolean {
    return visibility == VISIBLE && alpha > 0
}

fun Activity.getColorForTint(@ColorRes id: Int): ColorStateList {
    return ColorStateList.valueOf(ResourcesCompat.getColor(resources, id, theme))
}

fun EditText.showKeyboard() {
    val inputMethodManager = getActivity()!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    requestFocus()
    inputMethodManager.showSoftInput(this, 0)
    setSelection(length())
}

fun View.getActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

fun View.gone(goneOrVisible: Boolean) {
    visibility = if (goneOrVisible) GONE else VISIBLE
}

fun View.gone() {
    visibility = GONE
}

fun View.visible(visibleOrGone: Boolean) {
    visibility = if (visibleOrGone) VISIBLE else GONE
}

fun View.visible() {
    visibility = VISIBLE
}

fun View.showHideFade(
    showOrHide: Boolean,
    duration: Long = resources.getInteger(android.R.integer.config_shortAnimTime).toLong(),
    endListener: () -> Unit = {}
) {
    if (showOrHide && visibility == VISIBLE && alpha == 1f) {
        bringToFront()
        return run(endListener)
    }
    if (!showOrHide && (visibility == GONE || alpha == 0f)) return run(endListener)
    animate().setDuration(duration)
        .alpha((if (showOrHide) 1 else 0).toFloat())
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                visibility = if (showOrHide) VISIBLE else GONE
                run(endListener)
            }

            override fun onAnimationStart(animation: Animator?) {
                visibility = VISIBLE
            }
        })
}

fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}