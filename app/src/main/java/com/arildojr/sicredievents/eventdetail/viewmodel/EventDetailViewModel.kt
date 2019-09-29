package com.arildojr.sicredievents.eventdetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.arildojr.data.event.EventRepository
import com.arildojr.data.event.dto.CheckInUserDTO
import com.arildojr.data.event.model.Event
import com.arildojr.sicredievents.core.base.BaseViewModel
import kotlinx.coroutines.launch

class EventDetailViewModel(private val eventRepository: EventRepository) : BaseViewModel() {

    var event: Event? = null

    private val _checkInResult = MutableLiveData<Boolean>()
    val checkInResult: LiveData<Boolean> = Transformations.map(_checkInResult) { it }

    fun checkIn(request: CheckInUserDTO) = launch {
        _checkInResult.postValue(eventRepository.checkIn(request))
    }
}