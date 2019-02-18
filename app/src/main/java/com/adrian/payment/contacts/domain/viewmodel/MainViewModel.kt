package com.adrian.payment.contacts.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.adrian.payment.common.*
import com.adrian.payment.contacts.domain.ContactsPagingDataSource.Companion.PAGES_CONTACTS_SIZE
import com.adrian.payment.contacts.domain.ContactsPagingDataSourceFactory
import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.usecase.GetContacts

class MainViewModel(private val getContacts: GetContacts) : BaseViewModel() {

    lateinit var gamesList: LiveData<PagedList<Contact>>
    var contactsSelectedData: MutableLiveData<List<Contact>> = MutableLiveData()
    var contactsSelected: ArrayList<Contact> = ArrayList()
    lateinit var networkState: LiveData<NetworkState>
    lateinit var initialLoaderState: LiveData<NetworkState>

    private val pagedListConfig by lazy {
        Config(PAGES_CONTACTS_SIZE,
                PAGES_CONTACTS_SIZE * 2,
                false,
                PAGES_CONTACTS_SIZE)
    }

    init {
        resetList()
    }

    fun resetList() {
        val sourceFactory = ContactsPagingDataSourceFactory(disposables, getContacts)
        networkState = Transformations.switchMap(sourceFactory.usersDataSourceLiveData) {
            it.networkStateData
        }
        initialLoaderState = Transformations.switchMap(sourceFactory.usersDataSourceLiveData) {
            it.initialLoader
        }
        gamesList = sourceFactory.toLiveData(pagedListConfig)
    }

    fun addContactSelected(contact: Contact) =
            contactsSelected.addPostLiveData(contact, contactsSelectedData)

    fun removeContactData(contact: Contact) =
            contactsSelected.removePostLiveData(contact, contactsSelectedData)

    fun clearContactSelected() =
            contactsSelected.clearPostLiveData(contactsSelectedData)
}