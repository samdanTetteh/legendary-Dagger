package com.ijikod.app.githubapi

import com.ijikod.di.githubapi.GitHubApi
import com.ijikod.di.githubapi.model.RepoApiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeGitHubApi @Inject constructor() : GitHubApi {

    val repos = listOf<RepoApiModel>()


    override fun getTopRepositories(): List<RepoApiModel> {
        return repos
    }

}
