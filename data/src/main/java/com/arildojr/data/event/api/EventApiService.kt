package com.arildojr.data.event.api

import com.arildojr.data.event.dto.CheckInUserDTO
import com.arildojr.data.event.model.Event
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventApiService {

    @GET("/events")
    suspend fun getEvents(): Response<List<Event>>

    @POST("/checkin")
    suspend fun checkIn(@Body request: CheckInUserDTO): Response<ResponseBody>
}