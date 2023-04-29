package com.mvvm.cryptocapital.di

import com.mvvm.cryptocapital.ui.fragment.coinDetail.viewmodel.CoinDetailViewModel
import com.mvvm.cryptocapital.ui.fragment.coinList.viewmodel.CoinListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { CoinListViewModel(get(),get()) }
    viewModel { CoinDetailViewModel(get()) }
}