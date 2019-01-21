package com.adrian.payment.main.usecase

import com.adrian.payment.main.datasource.RunsDataSource
import com.adrian.payment.main.domain.model.GameInfo
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetGames @Inject constructor(private val runsDataSource: RunsDataSource) {

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