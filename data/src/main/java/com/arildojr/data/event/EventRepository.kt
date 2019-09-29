package com.arildojr.data.event

import com.arildojr.data.event.dto.CheckInUserDTO
import com.arildojr.data.event.model.Event

interface EventRepository {

    suspend fun getEvents(): List<Event>

    suspend fun checkIn(request: CheckInUserDTO): Boolean
}