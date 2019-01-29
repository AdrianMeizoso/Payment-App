package com.adrian.payment.contacts.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarvelResponse(
        val code: Int,
        val status: String,
        @Json(name = "data") val marvelData: MarvelData)

@JsonClass(generateAdapter = true)
data class MarvelData(
        val offset: Int,
        val limit: Int,
        @Json(name = "results") val marvelHeroes: List<MarvelHero>)

data class MarvelHero(
        val id: Int,
        val name: String,
        val description: String,
        val thumbnail: MarvelThumbnail)

data class MarvelThumbnail(
        val path: String,
        val extension: String)