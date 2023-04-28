package com.cryptocapital.di

import com.cryptocapital.ui.fragment.coinDetail.viewmodel.CoinDetailViewModel
import com.cryptocapital.ui.fragment.coinList.viewmodel.CoinListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { CoinListViewModel(get(),get()) }
    viewModel { CoinDetailViewModel(get()) }
}