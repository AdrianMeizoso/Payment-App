package com.adrian.payment

import android.app.Application
import com.adrian.payment.common.injection.appModule
import com.adrian.payment.main.module.mainModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class PaymentApp : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(appModule)
        import(mainModule)
    }
}