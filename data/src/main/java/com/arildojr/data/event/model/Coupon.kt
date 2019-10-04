package com.arildojr.data.event.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coupon(
    val id: String? = null,
    val eventId: String? = null,
    val discount: Int = 0
) : Parcelable