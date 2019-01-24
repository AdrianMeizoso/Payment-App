package com.adrian.payment.contacts.domain

import androidx.paging.PageKeyedDataSource
import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.usecase.GetContacts
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ContactsPagingDataSource(
        private val getContacts: GetContacts,
        private val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, Contact>() {

    companion object {
        const val PAGES_CONTACTS_SIZE: Int = 40
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Contact>) {

        compositeDisposable.add(getContacts.execute(PAGES_CONTACTS_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { contactsList: List<Contact>?, _: Throwable? ->
                    contactsList?.let { callback.onResult(it, 0, PAGES_CONTACTS_SIZE) }
                })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Contact>) {

        getContacts.offset = params.key
        compositeDisposable.add(getContacts.execute(PAGES_CONTACTS_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { contactsList: List<Contact>?, _: Throwable? ->
                    contactsList?.let { callback.onResult(it, params.key + PAGES_CONTACTS_SIZE) }
                })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Contact>) {}
}