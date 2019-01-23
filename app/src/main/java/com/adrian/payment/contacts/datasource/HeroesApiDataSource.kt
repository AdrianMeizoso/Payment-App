package com.adrian.payment.contacts.datasource

import com.adrian.payment.contacts.domain.model.MarvelResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesApiDataSource {

    @GET("characters")
    fun getHeroes(@Query("ts") timestamp: String,
                  @Query("apikey") apiKey: String,
                  @Query("hash") hash: String,
                  @Query("offset") offset: Int)
            : Single<MarvelResponse>
}