package `in`.tilicho.android.duolingobutton.utils

import android.content.Context
import android.util.TypedValue


fun Context.dpToPx(dp: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun Context.spToPx(sp: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (sp / scale).toInt()
}

fun Context.dpToSp(dp: Float): Int {
    return (this.dpToPx(dp) / this.resources.displayMetrics.scaledDensity).toInt()
}