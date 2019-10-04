package com.arildojr.events.eventdetail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.arildojr.data.event.EventRepository
import com.arildojr.data.event.dto.CheckInUserDTO
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EventDetailViewModelTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repo: EventRepository
    private lateinit var eventDetailViewModel: EventDetailViewModel
    private val testCheckInRequest = CheckInUserDTO(
        "eventId1", "Arildo Borges Jr", "sitesabj@gmail.com"
    )

    @Before
    fun setUp() {
        repo = mock()
        whenever(runBlocking { repo.checkIn(testCheckInRequest) }).thenReturn(
            true
        )
        eventDetailViewModel = EventDetailViewModel(repo)

    }

    @Test
    fun `check-in, when post data request, then return true if success`() {
        runBlocking {
            val eventsLiveData = eventDetailViewModel.checkInResult
            val observer = mock() as Observer<Boolean>
            eventsLiveData.observeForever(observer)

            eventDetailViewModel.checkIn(testCheckInRequest)


            // verify if liveData value is true
            verify(observer).onChanged(true)
        }


    }

    @Test
    fun `validate e-mail, when send valid e-mail, then return true`() = runBlocking {
        val validEmails = listOf("sitesabj@gmail.com", "arildo@somosdx.co")

        validEmails.forEach { email ->
            // verify if email is valid
            assertEquals(true, eventDetailViewModel.isValidEmail(email))
        }
    }

    @Test
    fun `validate e-mail, when send invalid e-mail, then return false`() = runBlocking {
        val invalidEmails =
            listOf("sitesabj@com", "sitesabj@gmail.com.", "@somosdx.co", "somosdx.com")

        invalidEmails.forEach { email ->
            // verify is email is invalid
            assertEquals(false, eventDetailViewModel.isValidEmail(email))
        }
    }

}