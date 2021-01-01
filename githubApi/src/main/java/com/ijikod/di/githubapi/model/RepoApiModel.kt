package com.ijikod.di.githubapi.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoApiModel(
    val id: Long,
    val name: String,
    val description: String?,
    val owner: UserApiModel,
    @Json(name= "stargazers_count") val stargazersCount: Int,
    @Json(name= "forks") val forkCount: Int,
    @Json(name= "contributors_url")val contributorsUrl: String,
    @Json(name= "created_at")val createdDate: String,
    @Json(name= "updated_at")val updatedDate: String
)