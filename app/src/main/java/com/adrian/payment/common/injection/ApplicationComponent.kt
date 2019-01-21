package com.adrian.payment.common.injection

import com.adrian.payment.PaymentApp
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = [AppModule::class, DataModule::class, AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class, InjectorBuilder::class])
interface ApplicationComponent : AndroidInjector<PaymentApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: PaymentApp): Builder
        fun build(): ApplicationComponent
    }

    override fun inject(app: PaymentApp)
}
