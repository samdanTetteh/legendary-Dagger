package com.ijikod.details

import com.ijikod.details.list.ContributorItem

sealed class RepoInfoViewSate
object RepoInfoViewStateLoading: RepoInfoViewSate()
data class RepoInfoViewStateLoaded (
    val repoName: String,
    val repoDescription: String,
    val createdDate: String,
    val updatedDate: String
) : RepoInfoViewSate()

sealed class RepoContributorsViewState
object RepoContributorsViewStateLoading: RepoContributorsViewState()
data class RepoContributorsViewStateLoaded (val contributors: List<ContributorItem>) : RepoContributorsViewState()