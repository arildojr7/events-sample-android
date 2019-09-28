package com.arildojr.sicredievents.core.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("bind:data")
fun <T> RecyclerView.setRecyclerViewProperties(data: T?) {
        (adapter as BindableAdapter<T>).setData(data)
}