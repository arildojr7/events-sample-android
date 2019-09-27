package com.arildojr.data.event

import com.arildojr.data.event.model.Event

interface EventDataSource {

    suspend fun getEvents(): List<Event>
}