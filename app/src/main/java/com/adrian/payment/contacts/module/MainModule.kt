package com.adrian.payment.contacts.module

import com.adrian.payment.contacts.domain.viewmodel.AmountModelFactory
import com.adrian.payment.contacts.domain.viewmodel.MainViewModelFactory
import com.adrian.payment.contacts.usecase.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val mainModule = Kodein.Module("Main") {
    bind() from singleton { GetGames(instance()) }
    bind() from singleton { GetSpeedRun(instance()) }
    bind() from singleton { GetUser(instance()) }
    bind() from singleton { GetDeviceContacts(instance())}
    bind() from singleton { GetMarvelContacts(instance())}

    bind() from provider { MainViewModelFactory(instance(), instance(), instance(), instance(), instance()) }
    bind() from provider { AmountModelFactory() }
}