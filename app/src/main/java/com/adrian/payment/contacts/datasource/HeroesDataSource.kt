package com.adrian.payment.contacts.datasource

import com.adrian.payment.contacts.domain.model.MarvelResponse
import io.reactivex.Single

interface HeroesDataSource {

    fun getHeroes(offset: Int): Single<MarvelResponse>
}