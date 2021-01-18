package com.ijikod.di.repository

import com.ijikod.di.githubapi.GitHubApi
import com.ijikod.di.githubapi.model.ContributorApiModel
import com.ijikod.di.githubapi.model.RepoApiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val gitHubApi: GitHubApi
) {

    private val cachedRepos = mutableListOf<RepoApiModel>()

    suspend fun getTopGitHubRepos(): List<RepoApiModel> {
        if (cachedRepos.isEmpty()){
            cachedRepos.addAll(gitHubApi.getTopRepositories().items)
        }
        return cachedRepos
    }

    suspend fun getRepo(repoOwner: String, repoName: String): RepoApiModel {
        return getRepoFromCache(repoOwner, repoName) ?: gitHubApi.getRepo(repoOwner, repoOwner)
    }

    suspend fun getContributors(repoOwner: String, repoName: String): List<ContributorApiModel> {
        return gitHubApi.getContributors(repoOwner, repoName)
    }


    private fun getRepoFromCache(repoOwner: String, repoName: String): RepoApiModel?{
        return cachedRepos.firstOrNull {repo ->
            repo.owner.login == repoOwner && repo.name == repoName
        }
    }









}