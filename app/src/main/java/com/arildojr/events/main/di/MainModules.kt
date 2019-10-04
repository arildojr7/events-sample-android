package com.arildojr.events.main.di

import com.arildojr.events.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val mainViewModelModule = module {
    viewModel { MainViewModel(get()) }
}

fun getMainModules() = listOf(mainViewModelModule)
