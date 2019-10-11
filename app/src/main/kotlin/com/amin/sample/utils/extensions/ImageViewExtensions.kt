package com.amin.sample.utils.extensions

import android.graphics.drawable.Animatable2
import android.widget.ImageView

fun ImageView.stop() {
    (drawable as? Animatable2)?.stop()
}

fun ImageView.start() {
    (drawable as? Animatable2)?.start()
}