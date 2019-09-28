package com.arildojr.sicredievents.main

import android.content.Intent
import android.os.Bundle
import com.arildojr.sicredievents.R
import com.arildojr.sicredievents.core.base.BaseActivity
import com.arildojr.sicredievents.databinding.ActivityMainBinding
import com.arildojr.sicredievents.eventdetail.EventDetailActivity
import com.arildojr.sicredievents.main.adapter.MainEventsAdapter
import com.arildojr.sicredievents.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var eventsAdapter: MainEventsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        setupRecyclerView()

        viewModel.getEvents()
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

    companion object {
        const val KEY_EVENT = "event"
        const val KEY_BUNDLE = "bundle"
    }
}
