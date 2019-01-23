package com.adrian.payment.contacts.usecase

import com.adrian.payment.contacts.datasource.RunsDataSource
import com.adrian.payment.contacts.domain.model.UserData
import io.reactivex.Single

class GetUser(private val runsDataSource: RunsDataSource) {

    fun execute(userId: String): Single<UserData> {
        return runsDataSource
                .getUser(userId)
                .flatMap {
                    Single.just(it.userData)
                }
                .doOnError {
                    it.printStackTrace()
                }
    }
}