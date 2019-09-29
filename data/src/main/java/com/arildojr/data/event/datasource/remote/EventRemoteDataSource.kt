package com.arildojr.data.event.datasource.remote

import com.arildojr.data.event.EventDataSource
import com.arildojr.data.event.api.EventApiService
import com.arildojr.data.event.dto.CheckInUserDTO
import com.arildojr.data.event.model.Event

class EventRemoteDataSource(private val apiService: EventApiService) : EventDataSource {
    override suspend fun getEvents(): List<Event> {
        return apiService.getEvents().body() ?: emptyList()
    }

    override suspend fun checkIn(request: CheckInUserDTO): Boolean {
        return apiService.checkIn(request).isSuccessful
    }
}