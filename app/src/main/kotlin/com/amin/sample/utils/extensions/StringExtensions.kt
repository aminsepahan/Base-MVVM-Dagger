package com.amin.leadme.utils.extensions

fun String.revertPersianNumbersAndRemoveColon(): String {
    val builder = StringBuilder()
    for (i in 0 until length) {
        when (this[i]) {
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
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' -> builder.append(this[i])
            else -> {
            }
        }
    }
    return builder.toString()
}