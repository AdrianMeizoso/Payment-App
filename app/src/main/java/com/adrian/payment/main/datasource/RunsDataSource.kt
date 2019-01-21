package com.adrian.payment.main.datasource

import com.adrian.payment.main.domain.model.GamesResponse
import com.adrian.payment.main.domain.model.RunsResponse
import com.adrian.payment.main.domain.model.UserResponse
import io.reactivex.Single

interface RunsDataSource {

    fun getGames(offset: Int): Single<GamesResponse>
    fun getSpeedruns(gameId: String): Single<RunsResponse>
    fun getUser(userId: String): Single<UserResponse>

}