package com.arildojr.events.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.arildojr.data.event.EventRepository
import com.arildojr.data.event.model.Coupon
import com.arildojr.data.event.model.Event
import com.arildojr.data.event.model.Person
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
class MainViewModelTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repo: EventRepository
    private lateinit var mainViewModel: MainViewModel
    private val testEvents = listOf(
        Event(
            "event1",
            "Event Title 1",
            1570004343,
            "description",
            "http",
            32.00,
            "-49",
            "-34", listOf(Person("person1", "event1", "Arildo Borges Jr")),
            listOf(Coupon("coupon1", "eventId1", 11))
        ), Event(
            "event2",
            "Event Title 2",
            137000434343,
            "description2",
            "http2",
            34.00,
            "-43",
            "-22", listOf(Person("person2", "event2", "Michele Borges")),
            listOf(Coupon("coupon2", "eventId2", 14))
        )
    )

    @Before
    fun setUp() {
        repo = mock()
        whenever(runBlocking { repo.getEvents() }).thenReturn(testEvents)
        mainViewModel = MainViewModel(repo)
    }

    @Test
    fun `get events, when requested to get events, then set in liveData`() {
        runBlocking {
            val eventsLiveData = mainViewModel.events
            val observer = mock() as Observer<List<Event>>
            eventsLiveData.observeForever(observer)

            mainViewModel.getEvents()

            verify(observer).onChanged(testEvents)
        }
    }

    @Test
    fun `set internet access true, when have internet, then set true in viewModel`() {
        mainViewModel.setInternetAccess(true)

        assertEquals(true, mainViewModel.hasInternet)
    }

    @Test
    fun `set internet access false, when not have internet, then set false in viewModel`() {
        mainViewModel.setInternetAccess(false)

        assertEquals(false, mainViewModel.hasInternet)
    }
}