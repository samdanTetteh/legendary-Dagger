package com.ijikod.di.githubapi

import com.ijikod.di.githubapi.model.RepoApiModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopRepoSearchResult (val items : List<RepoApiModel>)