package com.star_zero.pagingretrofitsample.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Repo(
    @Json(name = "id")
    val id: String,
    @Json(name = "full_name")
    val fullName: String
)
