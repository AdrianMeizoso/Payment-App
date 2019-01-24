package com.adrian.payment.contacts.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.adrian.payment.common.BaseViewModel
import com.adrian.payment.contacts.domain.ContactsPagingDataSource.Companion.PAGES_CONTACTS_SIZE
import com.adrian.payment.contacts.domain.ContactsPagingDataSourceFactory
import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.usecase.GetContacts
import com.adrian.payment.contacts.usecase.GetDeviceContacts
import com.adrian.payment.contacts.usecase.GetMarvelContacts

class MainViewModel(getContacts: GetContacts) : BaseViewModel() {

    val gamesList: LiveData<PagedList<Contact>>
    val contactsSelectedData: MutableLiveData<List<Contact>> = MutableLiveData()
    var contactsSelected: ArrayList<Contact> = ArrayList()

    private val pagedListConfig by lazy {
        PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(PAGES_CONTACTS_SIZE)
                .setPageSize(PAGES_CONTACTS_SIZE)
                .setPrefetchDistance(PAGES_CONTACTS_SIZE)
                .build()
    }

    init {
        val sourceFactory = ContactsPagingDataSourceFactory(disposables, getContacts)
        gamesList = LivePagedListBuilder(sourceFactory, pagedListConfig).build()
    }

    fun addContactSelected(contact: Contact) {
        contactsSelected.add(contact)
        contactsSelectedData.value = contactsSelected
    }

    fun removeContactData(contact: Contact) {
        contactsSelected.remove(contact)
        contactsSelectedData.value = contactsSelected
    }
}