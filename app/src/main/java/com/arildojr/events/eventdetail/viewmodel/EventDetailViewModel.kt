package com.arildojr.events.eventdetail.viewmodel

import android.text.TextUtils
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.arildojr.data.event.EventRepository
import com.arildojr.data.event.dto.CheckInUserDTO
import com.arildojr.data.event.model.Event
import com.arildojr.events.core.base.BaseViewModel

class EventDetailViewModel(private val eventRepository: EventRepository) : BaseViewModel() {

    var event: Event? = null

    private val _checkInResult = MutableLiveData<Boolean>()
    val checkInResult: LiveData<Boolean> = Transformations.map(_checkInResult) { it }

    suspend fun checkIn(request: CheckInUserDTO) {
        _checkInResult.postValue(eventRepository.checkIn(request))
    }

    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && EMAIL_ADDRESS.matcher(email).matches()
    }
}