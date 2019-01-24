package com.adrian.payment.contacts.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.domain.model.GameInfo
import com.adrian.payment.contacts.usecase.GetGames
import com.adrian.payment.contacts.usecase.GetMarvelContacts
import io.reactivex.disposables.CompositeDisposable

class GamesPagingDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                                   private val getMarvelContacts: GetMarvelContacts)
    : DataSource.Factory<Int, Contact>() {

    private val usersDataSourceLiveData = MutableLiveData<GamesPagingDataSource>()

    override fun create(): DataSource<Int, Contact> {
        val gamesPagingDataSource = GamesPagingDataSource(getMarvelContacts, compositeDisposable)
        usersDataSourceLiveData.postValue(gamesPagingDataSource)
        return gamesPagingDataSource
    }
}