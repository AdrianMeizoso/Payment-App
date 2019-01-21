package com.adrian.payment.main.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RunsResponse(
        @Json(name = "data") val runDataList: List<RunData>)

data class RunData(
    val id: String,
    val videos: RunVideos?,
    val times: RunTimes?,
    val players: List<RunPlayer>?
)

data class RunVideos(
    val links: List<RunLink>?)

data class RunPlayer(
    val id: String?)

data class RunLink(
    val uri: String?
)

data class RunTimes(
    val primary: String?,
    val primary_t: Long)