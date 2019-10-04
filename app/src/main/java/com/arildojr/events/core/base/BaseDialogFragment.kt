package com.arildojr.events.core.base

import android.util.Log
import androidx.fragment.app.DialogFragment
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseDialogFragment : DialogFragment(), CoroutineScope {

    private val fragmentExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d(
                ">>>CoroutineExcpHndlr",
                "coroutineContext: $coroutineContext throwable: ${throwable.printStackTrace()}"
            )
        }

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + fragmentExceptionHandler

}