package com.adrian.payment.contacts.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.adrian.payment.common.*
import com.adrian.payment.contacts.domain.paging.ContactsPagingDataSource.Companion.PAGES_CONTACTS_SIZE
import com.adrian.payment.contacts.domain.paging.ContactsPagingDataSourceFactory
import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.state.ContactListState
import com.adrian.payment.contacts.usecase.GetContacts

class MainViewModel(private val getContacts: GetContacts) : BaseViewModel<ContactListState>() {

    lateinit var gamesList: LiveData<PagedList<Contact>>
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

    private fun resetList() {
        val sourceFactory = ContactsPagingDataSourceFactory(disposables, getContacts)
        networkState = Transformations.switchMap(sourceFactory.usersDataSourceLiveData) {
            it.networkStateData
        }
        initialLoaderState = Transformations.switchMap(sourceFactory.usersDataSourceLiveData) {
            it.initialLoader
        }
        gamesList = sourceFactory.toLiveData(pagedListConfig)
    }

    //State changes from view

    fun onLoadingSecondNetworkState() {
        state.value = ContactListState.SecondLoading
    }

    fun onErrorSecondNetworkState(throwable: Throwable) {
        state.value = ContactListState.SecondLoadError(throwable)
    }

    fun onSuccessSecondNetworkState() {
        state.value = ContactListState.SecondLoadSuccess
    }

    fun onLoadingMainNetworkState() {
        state.value = ContactListState.MainLoading
    }

    fun onSuccessMainNetworkState() {
        state.value = ContactListState.MainLoadSuccess
    }

    fun onErrorMainNetworkState(throwable: Throwable) {
        state.value = ContactListState.MainLoadError(throwable)
    }

    fun onClickedContactListener(contact: Contact, position: Int) {
        if (contactsSelected.isNotEmpty()) onLongContactClicked(contact, position)
        else state.value = ContactListState.MainLoadSuccess
    }

    fun onLongContactClicked(contact: Contact, position: Int){
        if (contact.selected) contactsSelected.remove(contact)
        else contactsSelected.add(contact)
        state.value = ContactListState.RePainting(position)
    }

    fun onRepainting(){
        if (contactsSelected.isNotEmpty()) state.value = ContactListState.Selecting
        else state.value = ContactListState.MainLoadSuccess
    }
}