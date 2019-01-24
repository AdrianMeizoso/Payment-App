package com.adrian.payment.contacts.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.usecase.GetMarvelContacts
import io.reactivex.disposables.CompositeDisposable

class ContactsPagingDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                                      private val getMarvelContacts: GetMarvelContacts)
    : DataSource.Factory<Int, Contact>() {

    private val usersDataSourceLiveData = MutableLiveData<ContactsPagingDataSource>()

    override fun create(): DataSource<Int, Contact> {
        val gamesPagingDataSource = ContactsPagingDataSource(getMarvelContacts, compositeDisposable)
        usersDataSourceLiveData.postValue(gamesPagingDataSource)
        return gamesPagingDataSource
    }
}