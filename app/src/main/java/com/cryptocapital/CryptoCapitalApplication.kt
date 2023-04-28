package com.cryptocapital

import android.app.Application
import com.cryptocapital.data.remote.settings.Setting
import com.cryptocapital.di.appModule
import com.cryptocapital.di.dataKoinModule
import com.cryptocapital.utils.AppConstants
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptoCapitalApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        Setting.BASE_URL = AppConstants.BASE_URL
        startKoin {
            androidContext(this@CryptoCapitalApplication)
            modules(appModule, dataKoinModule)
        }
    }

}