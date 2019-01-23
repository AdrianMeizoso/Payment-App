package com.adrian.payment.contacts.module

import com.adrian.payment.contacts.domain.viewmodel.MainViewModelFactory
import com.adrian.payment.contacts.usecase.GetGames
import com.adrian.payment.contacts.usecase.GetSpeedRun
import com.adrian.payment.contacts.usecase.GetUser
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val mainModule = Kodein.Module("Main") {
    bind() from singleton { GetGames(instance()) }
    bind() from singleton { GetSpeedRun(instance()) }
    bind() from singleton { GetUser(instance()) }

    bind() from provider { MainViewModelFactory(instance(), instance(), instance()) }
}