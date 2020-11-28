package com.ijikod.di.githubapi.model

data class RepoApiModel(
    val id: Long,
    val name: String,
    val description: String,
    val owner: UserApiModel,
    val stargazersCount: Int,
    val forkCount: Int,
    val starCount: Int,
    val contributorsUrl: String,
    val createdDate: String,
    val updatedDate: String
)