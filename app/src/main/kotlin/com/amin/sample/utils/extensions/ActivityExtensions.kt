@file:Suppress("unused")

package com.amin.sample.utils.extensions

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import com.amin.leadme.utils.extensions.toast
import com.amin.sample.R
import com.amin.sample.utils.Snippets
import com.google.android.material.snackbar.Snackbar
import java.util.*


fun Context.showDismissDialog(
    message: String?, okBtnText: String = getString(R.string.thanks),
    okListener: () -> Unit = {}, id: Int = 0
) {
    Snippets.hideKeyboard(this as Activity, false)
    if (message.isNullOrEmpty())
        showMessageDialog("", okBtnText, okListener, showNegativeBtn = false, id = id)
    else
        showMessageDialog(message, okBtnText, okListener, showNegativeBtn = false, id = id)

}

fun Activity.showKeyboard() {
    (Objects.requireNonNull<Any>(getSystemService(Activity.INPUT_METHOD_SERVICE)) as InputMethodManager)
        .toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun Activity.setStatusBarColor(color: Int) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
    }
}

fun Activity.copyToClipboard(text: CharSequence) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("label", text)
    clipboard.setPrimaryClip(clip)
    toast(getString(R.string.copied))
}

fun Context.showMessageDialog(
    message: String?, okBtnText: String = getString(R.string.yes),
    okListener: () -> Unit = {}, noBtnText: String = getString(R.string.no),
    noListener: () -> Unit = {},
    showNegativeBtn: Boolean = true,
    id: Int = 0
) {
    Snippets.hideKeyboard(this as Activity, false)

    val builder = AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton(okBtnText) { _, _ -> run(okListener) }
    if (showNegativeBtn) {
        builder.setNegativeButton(noBtnText) { _, _ -> run(noListener) }
    }

    val dialog = builder.show()
    setFontsForDialog(dialog, id)
}

fun Context.dpToPixels(dp: Float): Int {
    return (dp * resources.displayMetrics.density).toInt()

}

fun Context.hideKeyboard(showOrHide: Boolean = false) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    if ((this as Activity).currentFocus != null) {
        if (showOrHide) {
            @Suppress("DEPRECATION")
            inputMethodManager.showSoftInputFromInputMethod(currentFocus!!.windowToken, 0)
            currentFocus!!.clearFocus()
        } else {
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }

    }
}

fun Activity.setFontsForDialog(dialog: AlertDialog, id: Int) {
    if (id == 0) return
    val font = ResourcesCompat.getFont(this, id)
    dialog.findViewById<TextView>(android.R.id.message)?.typeface = font
    dialog.findViewById<TextView>(android.R.id.button1)?.typeface = font
    dialog.findViewById<TextView>(android.R.id.button2)?.typeface = font
}

fun Activity.snack(
    message: String, buttonTxt: Int = R.string.ok,
    action: () -> Unit = {}, indefinite: Boolean = false, view: View? = window.decorView.rootView
) {
    var rootView = view
    if (rootView == null) rootView = window.decorView.rootView
    val snackBar: Snackbar = if (indefinite) {
        Snackbar.make(rootView!!, message, Snackbar.LENGTH_INDEFINITE)
    } else {
        Snackbar.make(rootView!!, message, Snackbar.LENGTH_LONG)
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        snackBar.setAction(buttonTxt) { }
            .setActionTextColor(resources.getColor(R.color.themeYellow, theme))
    } else {
        @Suppress("DEPRECATION")
        snackBar.setAction(buttonTxt) { run(action) }.setActionTextColor(resources.getColor(R.color.themeYellow))
    }
    snackBar.show()
}