package com.adrian.payment.contacts.datasource

import com.adrian.payment.contacts.domain.model.GamesResponse
import com.adrian.payment.contacts.domain.model.RunsResponse
import com.adrian.payment.contacts.domain.model.UserResponse
import io.reactivex.Single

interface RunsDataSource {

    fun getGames(offset: Int): Single<GamesResponse>
    fun getSpeedruns(gameId: String): Single<RunsResponse>
    fun getUser(userId: String): Single<UserResponse>

}