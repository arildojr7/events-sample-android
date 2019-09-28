package com.arildojr.sicredievents.eventdetail.di

import com.arildojr.sicredievents.eventdetail.viewmodel.EventDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val eventDetailViewModelModule = module {
    viewModel { EventDetailViewModel() }
}

fun getEventDetailModules() = listOf(eventDetailViewModelModule)
