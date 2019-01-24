package com.adrian.payment.contacts.domain

import androidx.paging.PageKeyedDataSource
import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.usecase.GetMarvelContacts
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ContactsPagingDataSource(
        private val getMarvelContacts: GetMarvelContacts,
        private val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, Contact>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Contact>) {

        compositeDisposable.add(getMarvelContacts.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { gameInfoList: List<Contact>?, _: Throwable? ->
                    gameInfoList?.let { callback.onResult(it, 0, 20) }
                })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Contact>) {

        getMarvelContacts.offset = params.key
        compositeDisposable.add(getMarvelContacts.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { gameInfoList: List<Contact>?, _: Throwable? ->
                    gameInfoList?.let { callback.onResult(it, params.key + 20) }
                })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Contact>) {}
}