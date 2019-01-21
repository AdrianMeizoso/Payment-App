package com.adrian.payment.main.usecase

import com.adrian.payment.main.datasource.RunsDataSource
import com.adrian.payment.main.domain.model.UserData
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUser @Inject constructor(private val runsDataSource: RunsDataSource) {

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