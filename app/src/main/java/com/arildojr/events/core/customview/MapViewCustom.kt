package com.arildojr.events.core.customview

import android.content.Context
import android.view.MotionEvent
import android.widget.FrameLayout
import android.view.ViewGroup
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.SupportMapFragment

class MapViewCustom : SupportMapFragment() {
    private var mListener: OnTouchListener? = null

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        savedInstance: Bundle?
    ): View? {
        val layout = super.onCreateView(layoutInflater, viewGroup, savedInstance)

        val frameLayout = TouchableWrapper(requireContext())

        frameLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))

        (layout as ViewGroup).addView(
            frameLayout,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        return layout
    }

    fun setListener(listener: OnTouchListener) {
        mListener = listener
    }

    interface OnTouchListener {
        fun onTouch()
    }

    inner class TouchableWrapper(context: Context) : FrameLayout(context) {

        override fun dispatchTouchEvent(event: MotionEvent): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> mListener?.onTouch()
                MotionEvent.ACTION_UP -> mListener?.onTouch()
            }
            return super.dispatchTouchEvent(event)
        }
    }
}