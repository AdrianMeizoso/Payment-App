package com.adrian.payment.contacts.repository

import com.adrian.payment.contacts.datasource.ContactsDeviceDataSource
import com.adrian.payment.contacts.datasource.RunsApiDataSource
import com.adrian.payment.contacts.datasource.RunsDataSource
import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.domain.model.GamesResponse
import com.adrian.payment.contacts.domain.model.RunsResponse
import com.adrian.payment.contacts.domain.model.UserResponse
import io.reactivex.Single

class ContactsRepository(private val contactsDeviceDataSource: ContactsDeviceDataSource) {

    fun getDeviceContacts(): Single<List<Contact>> = contactsDeviceDataSource.getDeviceContacts()
}