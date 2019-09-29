package com.arildojr.data.event

import com.arildojr.data.event.datasource.remote.EventRemoteDataSource
import com.arildojr.data.event.dto.CheckInUserDTO
import com.arildojr.data.event.model.Event

class EventRepositoryImpl(private val remoteDataSource: EventRemoteDataSource) : EventRepository {
    override suspend fun getEvents(): List<Event> {
        return remoteDataSource.getEvents()
    }

    override suspend fun checkIn(request: CheckInUserDTO): Boolean {
        return remoteDataSource.checkIn(request)
    }
}