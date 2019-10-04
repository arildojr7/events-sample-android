package com.arildojr.events.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.arildojr.events.R
import com.arildojr.events.core.base.BaseActivity
import com.arildojr.events.core.util.hasInternet
import com.arildojr.events.databinding.ActivityMainBinding
import com.arildojr.events.eventdetail.EventDetailActivity
import com.arildojr.events.main.adapter.MainEventsAdapter
import com.arildojr.events.main.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var eventsAdapter: MainEventsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.progressLoaderEventList.visibility = View.GONE

        launch {
            if (hasInternet(this@MainActivity)) {
                viewModel.setInternetAccess(true)
                viewModel.getEvents()
            } else {
                viewModel.setInternetAccess(false)
            }
        }
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        eventsAdapter = MainEventsAdapter(emptyList()) { event ->
            val bundle = Bundle().apply { putParcelable(KEY_EVENT, event) }
            val intent = Intent(this, EventDetailActivity::class.java)
            intent.putExtra(KEY_BUNDLE, bundle)
            startActivity(intent)
        }
        binding.rvEventList.adapter = eventsAdapter
    }

    override fun onConnectionChanged(isConnected: Boolean) {
        launch {
            viewModel.setInternetAccess(isConnected)
            if (isConnected) { viewModel.getEvents() }
            binding.invalidateAll()
        }
    }

    companion object {
        const val KEY_EVENT = "event"
        const val KEY_BUNDLE = "bundle"
    }
}
