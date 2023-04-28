package com.cryptocapital.di

import com.cryptocapital.data.remote.api.RestClient
import com.cryptocapital.data.repository.RemoteRepository
import org.koin.dsl.module

val dataKoinModule = module {
    single { RestClient(get()) }
    factory { RemoteRepository(get()) }
}