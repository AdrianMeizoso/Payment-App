package com.adrian.payment.contacts.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.adrian.payment.common.BaseViewModel
import com.adrian.payment.contacts.domain.ContactsPagingDataSourceFactory
import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.usecase.GetContacts
import com.adrian.payment.contacts.usecase.GetDeviceContacts
import com.adrian.payment.contacts.usecase.GetMarvelContacts

class MainViewModel(getContacts: GetContacts) : BaseViewModel() {

    val gamesList: LiveData<PagedList<Contact>>

    val contactsData: MutableLiveData<List<Contact>> = MutableLiveData()

    var position: Int = 0

    private val pagedListConfig by lazy {
        PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(40)
                .setPageSize(40)
                .setPrefetchDistance(40)
                .build()
    }

    init {
        val sourceFactory = ContactsPagingDataSourceFactory(disposables, getContacts)
        gamesList = LivePagedListBuilder(sourceFactory, pagedListConfig).build()

        /*disposables.add(getDeviceContacts.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { contacts, _: Throwable? ->
                    Log.v("CERDO", "Pintamos contactos: $contacts")
                    contactsData.value = contacts
                })*/

        /*disposables.add(getMarvelContacts.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { contacts, _: Throwable? ->
                    Log.v("CERDO", "Pintamos contactos: $contacts")
                    contactsData.value = contacts
                })*/
    }
}