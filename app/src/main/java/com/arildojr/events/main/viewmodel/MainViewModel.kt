package com.arildojr.events.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.arildojr.data.event.EventRepository
import com.arildojr.data.event.model.Event
import com.arildojr.events.core.base.BaseViewModel

class MainViewModel(private val eventRepository: EventRepository) : BaseViewModel() {

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> = Transformations.map(_events) { it }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = Transformations.map(_isLoading) { it }

    var hasInternet : Boolean = true
    private set

    suspend fun getEvents() {
        _isLoading.postValue(true)
        _events.postValue(eventRepository.getEvents())
        _isLoading.postValue(false)
    }

    fun setInternetAccess(hasInternet: Boolean) {
        this.hasInternet = hasInternet
    }
}