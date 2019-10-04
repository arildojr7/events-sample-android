package com.arildojr.events.eventdetail

import android.os.Bundle
import android.view.View
import com.arildojr.data.event.model.Event
import com.arildojr.events.R
import com.arildojr.events.core.base.BaseActivity
import com.arildojr.events.core.customview.MapViewCustom
import com.arildojr.events.core.extension.clickWithDebounce
import com.arildojr.events.core.util.shareContent
import com.arildojr.events.databinding.ActivityEventDetailBinding
import com.arildojr.events.eventdetail.customview.CheckInDialog
import com.arildojr.events.eventdetail.viewmodel.EventDetailViewModel
import com.arildojr.events.main.MainActivity.Companion.KEY_BUNDLE
import com.arildojr.events.main.MainActivity.Companion.KEY_EVENT
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.viewmodel.ext.android.viewModel

class EventDetailActivity :
    BaseActivity<ActivityEventDetailBinding>(R.layout.activity_event_detail),
    OnMapReadyCallback {

    private val viewModel: EventDetailViewModel by viewModel()

    private val mapFragment: MapViewCustom? by lazy { supportFragmentManager.findFragmentById(R.id.map) as MapViewCustom }
    private val event: Event? by lazy { viewModel.event }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra(KEY_BUNDLE)) {
            val bundle = intent.getBundleExtra(KEY_BUNDLE)
            viewModel.event = bundle?.getParcelable(KEY_EVENT)
            binding.event = bundle?.getParcelable(KEY_EVENT)
        }

        setupListeners()

        mapFragment?.getMapAsync(this)
    }

    private fun setupListeners() {
        binding.btnCheckIn.clickWithDebounce {
            CheckInDialog().show(supportFragmentManager, CHECKIN_DIALOG)
        }
        binding.fabShare.clickWithDebounce {
            shareContent(event?.title, event?.description, this)
        }
    }

    // region MAPS

    override fun onMapReady(map: GoogleMap) {
        val latitude = event?.latitude?.toDoubleOrNull()
        val longitude = event?.longitude?.toDoubleOrNull()

        if (latitude != null && longitude != null) {
            map.addMarker(
                MarkerOptions().position(
                    LatLng(
                        latitude,
                        longitude
                    )
                ).title(viewModel.event?.title)
            )
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), 18f))
            map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this@EventDetailActivity, R.raw.maps_style
                )
            )

            mapFragment?.setListener(object : MapViewCustom.OnTouchListener {
                override fun onTouch() {
                    binding.svContainer.requestDisallowInterceptTouchEvent(true)
                }
            })
        } else {
            binding.llLocalization.visibility = View.GONE
        }
    }

    // endregion

    companion object {
        const val CHECKIN_DIALOG = "checkin_dialog"
        const val ERROR_DIALOG = "no_internet_dialog"
    }
}
