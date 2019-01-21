package com.adrian.payment

import com.adrian.payment.common.injection.ApplicationComponent
import com.adrian.payment.common.injection.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PaymentApp : DaggerApplication() {

    private val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        applicationComponent.inject(this)
        return applicationComponent
    }
}