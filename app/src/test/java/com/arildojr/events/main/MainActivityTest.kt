package com.arildojr.events.main

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import com.arildojr.data.event.model.Coupon
import com.arildojr.data.event.model.Event
import com.arildojr.data.event.model.Person
import com.arildojr.events.main.adapter.MainEventsAdapter
import kotlinx.android.synthetic.main.item_main_event.view.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private lateinit var activity: MainActivity
    private lateinit var activityController: ActivityController<MainActivity>
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
        activityController = Robolectric.buildActivity(MainActivity::class.java)
        activity = activityController.get()
        activityController.create().start().visible()
    }

    @Test
    fun `load events, when set data in adapter, then show event list`() {
        val recycler = activity.binding.rvEventList

        val adapter = MainEventsAdapter(emptyList()) {}
        recycler.adapter = adapter
        adapter.setData(testEvents)

        recycler.update()

        val title1 = recycler.findViewHolderForAdapterPosition(0)?.itemView?.tvTitle?.text
        val title2 = recycler.findViewHolderForAdapterPosition(1)?.itemView?.tvTitle?.text

        Assert.assertEquals(testEvents[0].title, title1)
        Assert.assertEquals(testEvents[1].title, title2)
    }

    @Test
    fun `get item count, when request item count, then return number of items`() {
        val adapter = MainEventsAdapter(testEvents) {}

        //initial state
        val initialExpected = 2
        val initialActual = adapter.itemCount

        Assert.assertEquals(initialExpected, initialActual)
    }

    private fun RecyclerView.update() {
        this.measure(0, 0)
        this.layout(0, 0, 100, 1000)
    }
}