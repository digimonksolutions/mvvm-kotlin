package com.mvvm.cryptocapital.di

import com.mvvm.cryptocapital.data.remote.api.RestClient
import com.mvvm.cryptocapital.data.repository.RemoteRepository
import org.koin.dsl.module

val dataKoinModule = module {
    single { RestClient(get()) }
    factory { RemoteRepository(get()) }
}