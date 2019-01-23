package com.adrian.payment.contacts.usecase

import com.adrian.payment.contacts.datasource.RunsDataSource
import com.adrian.payment.contacts.domain.model.RunData
import io.reactivex.Single

class GetSpeedRun(private val runsDataSource: RunsDataSource) {

    fun execute(gameId:String): Single<RunData> {
        return runsDataSource
                .getSpeedruns(gameId)
                .flatMap {
                    if (it.runDataList.isEmpty()) {
                        Single.error(Throwable("Run data null"))
                    } else {
                        Single.just(it.runDataList.first())
                    }
                }
                .doOnError {
                    it.printStackTrace()
                }
    }
}