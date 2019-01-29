package com.adrian.payment.contacts.module

import com.adrian.payment.contacts.domain.viewmodel.AmountViewModel
import com.adrian.payment.contacts.domain.viewmodel.MainViewModel
import com.adrian.payment.contacts.usecase.GetContacts
import com.adrian.payment.contacts.usecase.GetDeviceContacts
import com.adrian.payment.contacts.usecase.GetMarvelContacts
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { GetDeviceContacts(get()) }
    single { GetMarvelContacts(get()) }
    single { GetContacts(get(), get()) }

    viewModel { MainViewModel(get()) }
    viewModel { AmountViewModel() }
}