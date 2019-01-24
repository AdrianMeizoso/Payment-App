package com.adrian.payment.contacts.usecase

import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.repository.ContactsRepository
import io.reactivex.Single

class GetDeviceContacts(private val contactsRepository: ContactsRepository) {

    fun execute(offset: Int, listSize: Int): Single<List<Contact>> {
        return contactsRepository
                .getDeviceContacts()
                .flatMap {
                    if (offset < it.count() && offset + listSize < it.count()) return@flatMap Single.just(it.subList(offset, offset + listSize))
                    else if (offset < it.count() && offset + listSize > it.count()) return@flatMap Single.just(it.subList(offset, it.count()))
                    else Single.just(emptyList<Contact>())
                }
                .doOnError {
                    it.printStackTrace()
                }
    }
}