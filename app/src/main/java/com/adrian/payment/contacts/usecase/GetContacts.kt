package com.adrian.payment.contacts.usecase

import com.adrian.payment.contacts.domain.model.Contact
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GetContacts(private val getMarvelContacts: GetMarvelContacts,
                  private val getDeviceContacts: GetDeviceContacts) {

    var offset: Int = 0

    fun execute(sizeList:Int): Single<List<Contact>> {
        return Single.zip(getMarvelContacts.execute(offset, sizeList),
                getDeviceContacts.execute(offset, sizeList),
                BiFunction<List<Contact>, List<Contact>, List<Contact>> { t1, t2 ->
                    val listResult = t1 + t2
                    listResult.sortedBy { contact -> contact.name }
                })
                .doOnError {
                    it.printStackTrace()
                }
                .doFinally { offset = 0 }
    }
}