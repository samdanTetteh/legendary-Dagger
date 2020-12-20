package com.ijikod.app.githubapi

import com.ijikod.di.githubapi.GitHubApi
import com.ijikod.di.githubapi.TopRepoSearchResult
import com.ijikod.di.githubapi.model.RepoApiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeGitHubApi @Inject constructor() : GitHubApi {

    var repos = listOf<RepoApiModel>()


    override suspend fun getTopRepositories(): TopRepoSearchResult {
        return TopRepoSearchResult(repos)
    }

}
