package com.adrian.payment.contacts.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AmountModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AmountViewModel::class.java)) {
            return AmountViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}