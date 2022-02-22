package `in`.tilicho.android.duolingobutton.utils

import android.content.Context
import android.util.TypedValue

class DisplayConversionUtils {

    companion object{

        fun dpFromPx(context: Context, px: Float): Float {
            return px / context.resources.displayMetrics.density
        }

        fun dpToPx(context: Context, valueInDp: Float): Float {
            val metrics = context.resources.displayMetrics
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                valueInDp,
                metrics
            )
        }
    }
}