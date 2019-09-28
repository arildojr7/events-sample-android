package com.arildojr.sicredievents.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.arildojr.data.event.EventRepository
import com.arildojr.data.event.model.Event
import com.arildojr.sicredievents.core.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val eventRepository: EventRepository) : BaseViewModel() {

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> = Transformations.map(_events) { it }

    fun getEvents() = launch {
        _events.postValue(eventRepository.getEvents())
    }
}