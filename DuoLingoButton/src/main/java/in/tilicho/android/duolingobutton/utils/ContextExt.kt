package `in`.tilicho.android.duolingobutton.utils

import android.content.Context
import android.util.TypedValue


fun Context.dpToPx(dp: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        this.resources.displayMetrics
    ).toInt()
}

fun Context.spToPx(sp: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp,
        this.resources.displayMetrics
    ).toInt()
}

fun Context.dpToSp(dp: Float): Int {
    return (this.dpToPx(dp) / this.resources.displayMetrics.scaledDensity).toInt()
}

fun Context.dp2Px(pixel: Int): Int {
    //final float scale = getResources().getDisplayMetrics().density;
    val scale = this.resources.displayMetrics.density
    return ((pixel - 0.5f)/ scale).toInt()//pixel/( scale + 0.5f).toInt()
}

fun Context.sp2Px(pixel: Int): Float {
    val scale = this.resources.displayMetrics.density
    return pixel * scale
}