package com.adrian.payment.main.module

import com.adrian.payment.main.MainViewModelFactory
import com.adrian.payment.main.usecase.GetGames
import com.adrian.payment.main.usecase.GetSpeedRun
import com.adrian.payment.main.usecase.GetUser
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val mainModule = Kodein.Module("Main") {
    bind() from singleton { GetGames(instance()) }
    bind() from singleton { GetSpeedRun(instance()) }
    bind() from singleton { GetUser(instance()) }

    bind() from provider { MainViewModelFactory(instance(),instance(),instance()) }
}