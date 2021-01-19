package com.ijikod.app.githubapi

import com.ijikod.di.githubapi.GitHubApi
import com.ijikod.di.githubapi.TopRepoSearchResult
import com.ijikod.di.githubapi.model.ContributorApiModel
import com.ijikod.di.githubapi.model.RepoApiModel
import java.lang.NullPointerException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeGitHubApi @Inject constructor() : GitHubApi {

    var repos = listOf<RepoApiModel>()
    var singleRepoResult: RepoApiModel? = null
    var contributorsResults = listOf<ContributorApiModel>()


    override suspend fun getTopRepositories(): TopRepoSearchResult {
        return TopRepoSearchResult(repos)
    }

    override suspend fun getRepo(repoOwner: String, repoName: String): RepoApiModel {
        return singleRepoResult ?: throw NullPointerException("singleRepoResult was not set")
    }

    override suspend fun getContributors(repoOwner: String, repoName: String): List<ContributorApiModel> {
        return contributorsResults
    }

}
