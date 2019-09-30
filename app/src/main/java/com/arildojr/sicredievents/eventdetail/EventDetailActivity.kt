package com.arildojr.sicredievents.eventdetail

import android.os.Bundle
import android.view.View
import com.arildojr.data.event.model.Event
import com.arildojr.sicredievents.R
import com.arildojr.sicredievents.core.base.BaseActivity
import com.arildojr.sicredievents.core.customview.MapViewCustom
import com.arildojr.sicredievents.core.customview.NoInternetDialog
import com.arildojr.sicredievents.core.extension.clickWithDebounce
import com.arildojr.sicredievents.core.util.hasInternet
import com.arildojr.sicredievents.core.util.shareContent
import com.arildojr.sicredievents.databinding.ActivityEventDetailBinding
import com.arildojr.sicredievents.eventdetail.customview.CheckInDialog
import com.arildojr.sicredievents.eventdetail.viewmodel.EventDetailViewModel
import com.arildojr.sicredievents.main.MainActivity.Companion.KEY_BUNDLE
import com.arildojr.sicredievents.main.MainActivity.Companion.KEY_EVENT
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch
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
            if (hasInternet(this)) {
                CheckInDialog().show(supportFragmentManager, CHECKIN_DIALOG)
            } else {
                NoInternetDialog().show(supportFragmentManager, NO_INTERNET_DIALOG)
            }
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
            launch {
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
            }
        } else {
            binding.llLocalization.visibility = View.GONE
        }
    }

    // endregion

    companion object {
        const val CHECKIN_DIALOG = "checkin_dialog"
        const val NO_INTERNET_DIALOG = "no_internet_dialog"
    }
}
