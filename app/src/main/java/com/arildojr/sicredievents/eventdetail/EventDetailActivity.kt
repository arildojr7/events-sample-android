package com.arildojr.sicredievents.eventdetail

import android.os.Bundle
import com.arildojr.sicredievents.R
import com.arildojr.sicredievents.core.base.BaseActivity
import com.arildojr.sicredievents.databinding.ActivityEventDetailBinding
import com.arildojr.sicredievents.eventdetail.viewmodel.EventDetailViewModel
import com.arildojr.sicredievents.main.MainActivity.Companion.KEY_BUNDLE
import com.arildojr.sicredievents.main.MainActivity.Companion.KEY_EVENT
import org.koin.android.viewmodel.ext.android.viewModel

class EventDetailActivity :
    BaseActivity<ActivityEventDetailBinding>(R.layout.activity_event_detail) {

    private val viewModel: EventDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra(KEY_BUNDLE)) {
            val bundle = intent.getBundleExtra(KEY_BUNDLE)
            binding.event = bundle?.getParcelable(KEY_EVENT)

        }
    }
}
