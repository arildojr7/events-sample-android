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
    val events: LiveData<List<Event>> = Transformations.map(_events) { it + it + it }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = Transformations.map(_isLoading) { it }

    var hasInternet : Boolean = true
    private set

    fun getEvents() = launch {
        _isLoading.postValue(true)
        _events.postValue(eventRepository.getEvents())
        _isLoading.postValue(false)
    }

    fun setInternetAccess(hasInternet: Boolean) {
        this.hasInternet = hasInternet
    }
}