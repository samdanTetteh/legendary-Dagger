package com.ijikod.di.githubapi.model

import com.squareup.moshi.Json

data class ContributorApiModel(
  val id: Long,
  val login: String,
  @Json(name = "avatar_url") val avatarUrl: String
)