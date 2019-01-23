package com.adrian.payment.contacts.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "data") val userData: UserData
)

data class UserData(
    val id: String,
    val names: UserNames
)

data class UserNames(
    val international: String?
)