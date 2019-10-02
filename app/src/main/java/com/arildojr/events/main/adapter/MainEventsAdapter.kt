package com.arildojr.events.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arildojr.data.event.model.Event
import com.arildojr.events.core.bindingadapter.BindableAdapter
import com.arildojr.events.databinding.ItemMainEventBinding

class MainEventsAdapter(
    private var items: List<Event>,
    private val openEventDetails: (Event) -> Unit
) :
    RecyclerView.Adapter<MainEventsAdapter.ViewHolder>(), BindableAdapter<List<Event>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainEventBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], this)

    override fun setData(data: List<Event>?) {
        this.items = data.orEmpty()
        notifyDataSetChanged()
    }

    fun onItemClicked(item: Event) {
        openEventDetails(item)
    }

    class ViewHolder(private val binding: ItemMainEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event, adapter: MainEventsAdapter) {
            binding.event = event
            binding.adapter = adapter
            binding.executePendingBindings()
        }
    }
}