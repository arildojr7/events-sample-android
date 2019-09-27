package com.arildojr.data.event.api

import com.arildojr.data.event.model.Event
import retrofit2.Response
import retrofit2.http.GET

interface EventApiService {

    @GET("/events")
    suspend fun getEvents(): Response<List<Event>>
}