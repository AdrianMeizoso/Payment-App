package com.adrian.payment.contacts.repository

import com.adrian.payment.common.Mockable
import com.adrian.payment.common.md5
import com.adrian.payment.contacts.datasource.ContactsDataSource
import com.adrian.payment.contacts.datasource.ContactsDeviceDataSource
import com.adrian.payment.contacts.datasource.ContactsFakeDataSource
import com.adrian.payment.contacts.datasource.HeroesApiDataSource
import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.domain.model.MarvelResponse
import io.reactivex.Single

@Mockable
class ContactsRepository(private val contactsDeviceDataSource: ContactsDataSource,
                         private val heroesApiDataSource: HeroesApiDataSource) {

    companion object {
        const val APIKEYPUBLIC = "cd4ea2ed65ba80284996b3663c7f2b97"
        const val APIKEYPRIVATE = "bc381ea87c7c0f18495430bb551ef8498220dd97"
    }

    fun getMarvelContacts(offset: Int, sizeList: Int): Single<MarvelResponse> {
        val millis = System.currentTimeMillis()
        val hash = "$millis$APIKEYPRIVATE$APIKEYPUBLIC".md5()
        return heroesApiDataSource.getHeroes( millis.toString(), APIKEYPUBLIC, hash, offset, sizeList)
    }

    fun getDeviceContacts(): Single<List<Contact>> = contactsDeviceDataSource.getDeviceContacts()
}