package com.arildojr.data.di

import com.arildojr.data.RetrofitInitializer
import com.arildojr.data.event.EventRepository
import com.arildojr.data.event.EventRepositoryImpl
import com.arildojr.data.event.datasource.remote.EventRemoteDataSource
import org.koin.dsl.module

private val apiServiceModule = module {
    single { RetrofitInitializer().eventApiService() }
}

private val repositoryModule = module {
    single<EventRepository> { EventRepositoryImpl(get()) }
}

private val dataSourceModule = module {
    single { EventRemoteDataSource(get()) }
}

fun getEventModules() = listOf(apiServiceModule, repositoryModule, dataSourceModule)