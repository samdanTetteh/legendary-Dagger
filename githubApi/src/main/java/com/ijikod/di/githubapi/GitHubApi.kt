package com.ijikod.di.githubapi

import com.ijikod.di.githubapi.model.RepoApiModel
import com.ijikod.di.githubapi.model.UserApiModel

interface GitHubApi {

    fun getTopRepositories(): List<RepoApiModel>
}

class MockGitHubApi: GitHubApi {
    override fun getTopRepositories(): List<RepoApiModel> {
        return listOf(
            RepoApiModel(
                id = 1L,
                name = "Samdan",
                description = "Developer from Ghana",
                owner = UserApiModel(id= 1L, login = "Dagger"),
                stargazersCount = 1,
                forksCount = 1,
                contributorsUrl = "",
                createdDate = "1/01/2021",
                updatedDate =  "1/02/2021"
            ),
            RepoApiModel(
                id = 1L,
                name = "Oluwale",
                description = "Developer from Nigeria",
                owner = UserApiModel(id= 1L, login = "Dagger"),
                stargazersCount = 1,
                forksCount = 1,
                contributorsUrl = "",
                createdDate = "1/01/2021",
                updatedDate =  "1/02/2021"
            )
        )
    }

}