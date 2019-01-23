package com.adrian.payment.contacts.repository

import com.adrian.payment.common.md5
import com.adrian.payment.contacts.datasource.ContactsDeviceDataSource
import com.adrian.payment.contacts.datasource.HeroesApiDataSource
import com.adrian.payment.contacts.datasource.RunsApiDataSource
import com.adrian.payment.contacts.datasource.RunsDataSource
import com.adrian.payment.contacts.domain.model.*
import io.reactivex.Single

class ContactsRepository(private val contactsDeviceDataSource: ContactsDeviceDataSource,
                         private val heroesApiDataSource: HeroesApiDataSource) {

    companion object {
        const val APIKEYPUBLIC = "cd4ea2ed65ba80284996b3663c7f2b97"
        const val APIKEYPRIVATE = "bc381ea87c7c0f18495430bb551ef8498220dd97"
    }

    fun getMarvelContacts(offset: Int): Single<MarvelResponse> {
        val millis = System.currentTimeMillis()
        val hash = "$millis$APIKEYPRIVATE$APIKEYPUBLIC".md5()
        return heroesApiDataSource.getHeroes( millis.toString(), APIKEYPUBLIC, hash, offset)
    }

    fun getDeviceContacts(): Single<List<Contact>> = contactsDeviceDataSource.getDeviceContacts()
}