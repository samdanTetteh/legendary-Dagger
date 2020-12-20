package com.ijikod.di.repository

import com.ijikod.di.githubapi.GitHubApi
import com.ijikod.di.githubapi.model.RepoApiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val gitHubApi: GitHubApi
) {

    suspend fun getTopGitHubRepos(): List<RepoApiModel> {
        return gitHubApi.getTopRepositories()
    }

}