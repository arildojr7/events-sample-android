package com.arildojr.sicredievents.core.base

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.arildojr.sicredievents.core.util.ActivityBindingProperty
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes val resId: Int
) : AppCompatActivity(), CoroutineScope {

    private val activityExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d(
                ">>>CoroutineExcpHndlr",
                "coroutineContext: $coroutineContext throwable: ${throwable.printStackTrace()}"
            )
        }

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + activityExceptionHandler

    val binding by activityBinding<T>(resId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

        subscribeUi()
    }

    /**
     * Override this method to observe livedata objects (optional)
     */
    open fun subscribeUi() {}

    /**
     * Override this method to check connectivity (optional)
     */
    private fun <T : ViewDataBinding> activityBinding(@LayoutRes resId: Int) =
        ActivityBindingProperty<T>(resId)
}