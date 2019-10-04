package com.arildojr.events.eventdetail.di

import com.arildojr.events.eventdetail.viewmodel.EventDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val eventDetailViewModelModule = module {
    viewModel { EventDetailViewModel(get()) }
}

fun getEventDetailModules() = listOf(eventDetailViewModelModule)
