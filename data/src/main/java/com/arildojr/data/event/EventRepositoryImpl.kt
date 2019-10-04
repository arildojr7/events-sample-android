package com.arildojr.data.event

import com.arildojr.data.event.datasource.remote.EventRemoteDataSource
import com.arildojr.data.event.dto.CheckInUserDTO
import com.arildojr.data.event.model.Event
import java.lang.Exception

class EventRepositoryImpl(private val remoteDataSource: EventRemoteDataSource) : EventRepository {
    override suspend fun getEvents(): List<Event>? {
        return try { remoteDataSource.getEvents() }
        catch (e: Exception) { null }
    }

    override suspend fun checkIn(request: CheckInUserDTO): Boolean {
        return remoteDataSource.checkIn(request)
    }
}