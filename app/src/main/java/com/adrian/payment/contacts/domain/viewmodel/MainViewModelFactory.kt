package com.adrian.payment.contacts.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adrian.payment.contacts.usecase.*

class MainViewModelFactory (
        private val getGames: GetGames,
        private val getSpeedRun: GetSpeedRun,
        private val getUser: GetUser,
        private val getDeviceContacts: GetDeviceContacts,
        private val getMarvelContacts: GetMarvelContacts) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getGames, getSpeedRun, getUser, getDeviceContacts, getMarvelContacts) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}