package com.adrian.payment.contacts.usecase

import com.adrian.payment.contacts.datasource.RunsDataSource
import com.adrian.payment.contacts.domain.model.GameInfo
import io.reactivex.Single

class GetGames(private val runsDataSource: RunsDataSource) {

    var offset: Int = 0

    fun execute(): Single<List<GameInfo>> {
        return runsDataSource
                .getGames(offset)
                .flatMap {
                    Single.just(it.gameInfoList)
                }
                .doOnError {
                    it.printStackTrace()
                }
                .doFinally { offset = 0 }
    }
}