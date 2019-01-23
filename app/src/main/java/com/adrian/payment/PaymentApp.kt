package com.adrian.payment

import android.app.Application
import com.adrian.payment.common.injection.appModule
import com.adrian.payment.contacts.module.mainModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class PaymentApp : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        bind() from singleton { applicationContext }
        import(appModule)
        import(mainModule)
    }
}