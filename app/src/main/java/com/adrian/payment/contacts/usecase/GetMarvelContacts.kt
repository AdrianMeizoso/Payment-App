package com.adrian.payment.contacts.usecase

import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.repository.ContactsRepository
import io.reactivex.Single

class GetMarvelContacts(private val contactsRepository: ContactsRepository) {

    fun execute(offset: Int, sizeList: Int): Single<List<Contact>> {
        return contactsRepository
                .getMarvelContacts(offset, sizeList)
                .toObservable()
                .flatMapIterable { it.marvelData.marvelHeroes }
                .map { hero -> Contact(hero.name,
                        null,
                        "${hero.thumbnail.path}/standard_fantastic.${hero.thumbnail.extension}" )
                }
                .toList()
                .doOnError {
                    it.printStackTrace()
                }
    }
}