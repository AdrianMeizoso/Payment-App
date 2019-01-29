package com.adrian.payment

import android.app.Application
import com.adrian.payment.common.injection.appModule
import com.adrian.payment.contacts.module.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PaymentApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PaymentApp)
            modules(appModule, mainModule)
        }
    }
}