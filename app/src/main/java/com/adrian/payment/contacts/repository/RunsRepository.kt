package com.adrian.payment.contacts.repository

import com.adrian.payment.contacts.datasource.RunsApiDataSource
import com.adrian.payment.contacts.datasource.RunsDataSource
import com.adrian.payment.contacts.domain.model.GamesResponse
import com.adrian.payment.contacts.domain.model.RunsResponse
import com.adrian.payment.contacts.domain.model.UserResponse
import io.reactivex.Single

class RunsRepository(private val runsApiDataSource: RunsApiDataSource) : RunsDataSource {

    override fun getGames(offset: Int): Single<GamesResponse> = runsApiDataSource.getGames(offset)

    override fun getSpeedruns(gameId: String): Single<RunsResponse> = runsApiDataSource.getSpeedruns(gameId)

    override fun getUser(userId: String): Single<UserResponse> = runsApiDataSource.getUser(userId)
}