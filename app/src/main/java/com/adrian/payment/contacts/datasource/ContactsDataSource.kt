package com.adrian.payment.contacts.datasource

import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.domain.model.MarvelResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactsDataSource {

    fun getDeviceContacts(): Single<List<Contact>>
}