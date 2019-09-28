package com.arildojr.sicredievents.main.di

import com.arildojr.sicredievents.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val mainViewModelModule = module {
    viewModel { MainViewModel(get()) }
}

fun getMainModules() = listOf(mainViewModelModule)
