package com.arildojr.events.core.base

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.arildojr.events.core.receiver.NetworkChangeReceiver
import com.arildojr.events.core.util.ActivityBindingProperty
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes val resId: Int
) : AppCompatActivity(), CoroutineScope, NetworkChangeReceiver.ConnectivityReceiverListener {

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
    private var networkChangeReceiver = NetworkChangeReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

        subscribeUi()
        registerReceiver(
            networkChangeReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onResume() {
        super.onResume()
        NetworkChangeReceiver.connectivityReceiverListener = this
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        onConnectionChanged(isConnected)
    }

    open fun onConnectionChanged(isConnected: Boolean) {}

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