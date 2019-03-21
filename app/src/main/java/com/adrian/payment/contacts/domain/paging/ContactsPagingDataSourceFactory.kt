package com.adrian.payment.contacts.domain.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.usecase.GetContacts
import io.reactivex.disposables.CompositeDisposable

class ContactsPagingDataSourceFactory(
        private val compositeDisposable: CompositeDisposable,
        private val getContacts: GetContacts)
    : DataSource.Factory<Int, Contact>() {

    val usersDataSourceLiveData = MutableLiveData<ContactsPagingDataSource>()

    override fun create(): DataSource<Int, Contact> {
        val contactsPagingDataSource = ContactsPagingDataSource(getContacts, compositeDisposable)
        usersDataSourceLiveData.postValue(contactsPagingDataSource)
        return contactsPagingDataSource
    }
}