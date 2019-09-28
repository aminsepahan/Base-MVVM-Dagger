package com.amin.sample.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.view.inputmethod.InputMethodManager

import java.text.DecimalFormat
import java.util.Calendar
import java.util.Objects
import java.util.concurrent.TimeUnit


/**
 * Created by Amin on 3/29/2015.
 * this class is place to save all those methods that are
 * going to be used multiple time in application and in different activities
 */
@Suppress("unused")
object Snippets {

    private val PERMISSIONS_STORAGE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    fun dpToPixels(dp: Float, context: Context): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale).toInt()
    }


    fun getDisplayWidth(activity: Activity): Int {
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }

    fun hideKeyboard(activity: Activity, showOrHide: Boolean) {
        val inputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (activity.currentFocus != null) {
            if (showOrHide) {
                inputMethodManager.hideSoftInputFromWindow(
                    activity.currentFocus!!.windowToken,
                    0
                )
                activity.currentFocus!!.clearFocus()
            } else {
                inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
            }

        }
    }

    fun showKeyboard(activity: Activity) {
        (Objects.requireNonNull(activity.getSystemService(Activity.INPUT_METHOD_SERVICE)) as InputMethodManager)
            .toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }


    fun getWidth(activity: Activity): Int {
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }

    fun getHeight(activity: Activity): Int {
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.y
    }

    fun convertTimestampToText(time: Long): String {
        var timestamp = time
        val calendar = Calendar.getInstance()
        timestamp = calendar.timeInMillis - timestamp

        return when {
            TimeUnit.MILLISECONDS.toDays(timestamp) > 31 -> (TimeUnit.MILLISECONDS.toDays(timestamp) % 31).toString() + " ماه پیش"
            TimeUnit.MILLISECONDS.toDays(timestamp) > 0 -> TimeUnit.MILLISECONDS.toDays(timestamp).toString() + " روز پیش"
            TimeUnit.MILLISECONDS.toHours(timestamp) > 0 -> TimeUnit.MILLISECONDS.toHours(timestamp).toString() + " ساعت پیش"
            TimeUnit.MILLISECONDS.toMinutes(timestamp) > 0 -> TimeUnit.MILLISECONDS.toMinutes(timestamp).toString() + " دقیقه پیش"
            else -> "کمتر از یک دقیقه پیش"
        }
    }

    fun formatPrice(num: String): String {
        val formatter = DecimalFormat("#,###,###")
        return formatter.format(java.lang.Long.valueOf(num))
    }

    fun formatPrice(num: Double): String {
        val formatter = DecimalFormat("#,###,###")
        return formatter.format(num)
    }

    fun formatPrice(num: Long?): String {
        val formatter = DecimalFormat("#,###,###")
        return formatter.format(num)
    }

    fun formatPriceWithCurrency(currency: String, num: Long?): String {
        val formatter = DecimalFormat("#,###,###")
        return formatter.format(num) + " " + currency
    }

    fun formatPriceWithCurrency(currency: String, num: Double?): String {
        val formatter = DecimalFormat("#,###,###")
        return formatter.format(num) + " " + currency
    }

    fun formatPriceWithCurrency(currency: String, num: String): String {
        val formatter = DecimalFormat("#,###,###")
        return formatter.format(java.lang.Long.valueOf(num)) + " " + currency
    }

    fun revertPersianNumbers(num: String): String {
        val builder = StringBuilder()
        for (i in num.indices) {
            when (num[i]) {
                '٠', '۰' -> builder.append(0)
                '١', '۱' -> builder.append(1)
                '٢', '۲' -> builder.append(2)
                '٣', '۳' -> builder.append(3)
                '٤', '۴' -> builder.append(4)
                '٥', '۵' -> builder.append(5)
                '٦', '۶' -> builder.append(6)
                '٧', '۷' -> builder.append(7)
                '٨', '۸' -> builder.append(8)
                '٩', '۹' -> builder.append(9)

                else -> builder.append(num[i])
            }
        }
        return builder.toString()
    }


}
