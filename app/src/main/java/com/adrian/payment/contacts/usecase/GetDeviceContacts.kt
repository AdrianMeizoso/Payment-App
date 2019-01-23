package com.adrian.payment.contacts.usecase

import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.repository.ContactsRepository
import io.reactivex.Single

class GetDeviceContacts(private val contactsRepository: ContactsRepository) {

    fun execute(): Single<List<Contact>> {
        return contactsRepository
                .getDeviceContacts()
                .doOnError {
                    it.printStackTrace()
                }
    }
}