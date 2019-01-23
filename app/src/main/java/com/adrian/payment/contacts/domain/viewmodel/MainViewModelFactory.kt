package com.adrian.payment.contacts.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adrian.payment.contacts.usecase.GetGames
import com.adrian.payment.contacts.usecase.GetSpeedRun
import com.adrian.payment.contacts.usecase.GetUser

class MainViewModelFactory (
        private val getGames: GetGames,
        private val getSpeedRun: GetSpeedRun,
        private val getUser: GetUser) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getGames, getSpeedRun, getUser) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}