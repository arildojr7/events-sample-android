package com.arildojr.sicredievents.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.arildojr.sicredievents.R
import com.arildojr.sicredievents.core.base.BaseActivity
import com.arildojr.sicredievents.databinding.ActivityMainBinding
import com.arildojr.sicredievents.main.adapter.MainEventsAdapter
import com.arildojr.sicredievents.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var eventsAdapter: MainEventsAdapter
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()

        viewModel.getEvents()
    }

    override fun subscribeUi() {
        viewModel.events.observe(this, Observer {
            eventsAdapter.updateList(it + it + it + it + it)
        })
    }

    private fun setupRecyclerView() {
        eventsAdapter = MainEventsAdapter(emptyList())
        binding.rvEventList.adapter = eventsAdapter
    }
}
