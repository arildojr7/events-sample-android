package com.arildojr.sicredievents.core.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DateFormat
import java.util.Date
import java.util.Locale

@BindingAdapter("bind:formatDate")
fun TextView.formatDate(formatDate: Long?) {
    formatDate?.let {
        this.text = formatDate(Date(it), DateFormat.SHORT)
    }
}

fun formatDate(date: Date, formatPattern: Int): String? {
    val format = DateFormat.getDateInstance(formatPattern, Locale("pt", "BR"))
    return format.format(date) ?: null
}