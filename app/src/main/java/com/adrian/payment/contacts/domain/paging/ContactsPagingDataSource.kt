package com.adrian.payment.contacts.domain.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.adrian.payment.common.NetworkState
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

    val networkStateData = MutableLiveData<NetworkState>()
    val initialLoader = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Contact>) {
        initialLoader.postValue(NetworkState.Loading)
        compositeDisposable.add(getContacts.execute(PAGES_CONTACTS_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { contactsList: List<Contact>?, _: Throwable? ->
                    contactsList?.let {
                        initialLoader.postValue(NetworkState.Success)
                        callback.onResult(it, 0, PAGES_CONTACTS_SIZE)
                    } ?: initialLoader.postValue(NetworkState.Error(Throwable("Marvel data null")))
                })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Contact>) {
        networkStateData.postValue(NetworkState.Loading)
        getContacts.offset = params.key
        compositeDisposable.add(getContacts.execute(PAGES_CONTACTS_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { contactsList: List<Contact>?, _: Throwable? ->
                    contactsList?.let {
                        networkStateData.postValue(NetworkState.Success)
                        callback.onResult(it, params.key + PAGES_CONTACTS_SIZE)
                    } ?: initialLoader.postValue(NetworkState.Error(Throwable("Marvel data null")))
                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Contact>) {}
}