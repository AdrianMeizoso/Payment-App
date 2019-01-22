package com.adrian.payment.main.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adrian.payment.main.usecase.GetGames
import com.adrian.payment.main.usecase.GetSpeedRun
import com.adrian.payment.main.usecase.GetUser

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