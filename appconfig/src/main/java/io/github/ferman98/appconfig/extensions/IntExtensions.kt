package io.github.ferman98.appconfig.extensions

import android.content.res.Resources
import android.util.TypedValue

fun Int.dp(): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()
}