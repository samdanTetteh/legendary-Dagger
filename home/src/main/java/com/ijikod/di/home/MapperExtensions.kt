package com.ijikod.di.home

import com.ijikod.di.githubapi.model.RepoApiModel
import com.ijikod.di.home.list.RepoItem

fun RepoApiModel.toRepoItem(): RepoItem {
    return RepoItem(
        ownerName = owner.login,
        name = name,
        description = description ?: "",
        startCount = stargazersCount,
        forkCount = forkCount
    )
}