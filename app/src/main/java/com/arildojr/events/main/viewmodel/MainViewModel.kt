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

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = Transformations.map(_isError) { it }

    var hasInternet : Boolean = true
    private set

    suspend fun getEvents() {
        _isLoading.postValue(true)
        _isError.postValue(false)

        val response = eventRepository.getEvents()

        if (response != null) {
            _events.postValue(response)
        } else {
            _isError.postValue(true)
        }
        _isLoading.postValue(false)
    }

    fun setInternetAccess(hasInternet: Boolean) {
        this.hasInternet = hasInternet
    }
}