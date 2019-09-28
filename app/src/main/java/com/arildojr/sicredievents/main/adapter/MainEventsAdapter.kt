package com.arildojr.sicredievents.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arildojr.data.event.model.Event
import com.arildojr.sicredievents.databinding.ItemMainEventBinding

class MainEventsAdapter(
    private var items: List<Event>
) :
    RecyclerView.Adapter<MainEventsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemMainEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(obj: Event) {
            binding.item = obj
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainEventBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

    }

    fun updateList(data: List<Event>) {
        this.items = data
        notifyDataSetChanged()
    }
}