package `in`.tilicho.android.duolingobutton.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class CustomViewLifecycleOwner : LifecycleOwner {

    private val lifecycleRegistry by lazy {
        LifecycleRegistry(this).also {
            it.handleLifecycleEvent(Lifecycle.Event.ON_START)
        }
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    fun onAttachedToWindow() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    fun onDetachedFromWindow() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }
}
