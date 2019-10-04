package com.arildojr.data.event.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(
    val id: String? = null,
    val eventId: String? = null,
    val name: String = "",
    val picture: String? = null
) : Parcelable