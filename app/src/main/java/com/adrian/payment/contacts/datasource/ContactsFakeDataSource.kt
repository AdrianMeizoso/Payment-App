package com.adrian.payment.contacts.datasource

import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.domain.model.MarvelResponse
import io.reactivex.Single

class ContactsFakeDataSource : ContactsDataSource {

    override fun getDeviceContacts(): Single<List<Contact>> = Single.just(listOf(
            Contact("aname1", "111111111", "https://urlbuena1"),
            Contact("aname2", "222222222", "https://urlbuena2"),
            Contact("aname3", "333333333", "https://urlbuena3"),
            Contact("aname4", "444444444", "https://urlbuena4"),
            Contact("aname5", "555555555", "https://urlbuena5"),
            Contact("aname6", "666666666", "https://urlbuena6")
    ))
}