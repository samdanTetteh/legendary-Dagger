package com.ijikod.di.githubapi

import com.ijikod.di.githubapi.model.RepoApiModel
import com.ijikod.di.githubapi.model.UserApiModel
import javax.inject.Inject
import javax.inject.Singleton

interface GitHubApi {

    suspend fun getTopRepositories(): TopRepoSearchResult
}

@Singleton
class MockGitHubApi @Inject constructor() : GitHubApi {
    override suspend fun getTopRepositories(): TopRepoSearchResult {
        return TopRepoSearchResult(
            listOf(
                RepoApiModel(
                    id = 1L,
                    name = "Samdan",
                    description = "Developer from Ghana",
                    owner = UserApiModel(id = 1L, login = "Dagger"),
                    stargazersCount = 1,
                    forkCount = 1,
                    starCount = 1,
                    contributorsUrl = "",
                    createdDate = "1/01/2021",
                    updatedDate = "1/02/2021"
                ),
                RepoApiModel(
                    id = 1L,
                    name = "Oluwale",
                    description = "Developer from Nigeria",
                    owner = UserApiModel(id = 1L, login = "Dagger"),
                    stargazersCount = 1,
                    forkCount = 1,
                    starCount = 1,
                    contributorsUrl = "",
                    createdDate = "1/01/2021",
                    updatedDate = "1/02/2021"
                )
            )
        )
    }

}