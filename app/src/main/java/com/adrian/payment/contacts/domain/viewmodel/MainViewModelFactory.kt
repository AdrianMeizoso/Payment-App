package com.adrian.payment.contacts.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adrian.payment.contacts.usecase.GetContacts
import com.adrian.payment.contacts.usecase.GetDeviceContacts
import com.adrian.payment.contacts.usecase.GetMarvelContacts

class MainViewModelFactory (private val getContacts: GetContacts) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getContacts) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}