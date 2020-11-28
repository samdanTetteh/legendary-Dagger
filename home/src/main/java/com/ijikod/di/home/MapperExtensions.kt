package com.ijikod.di.home

import com.ijikod.di.githubapi.model.RepoApiModel
import com.ijikod.di.home.list.RepoItem

fun RepoApiModel.toRepoItem(): RepoItem {
    return RepoItem(
        name = name,
        description = description,
        startCount = starCount,
        forkCount = forkCount
    )
}