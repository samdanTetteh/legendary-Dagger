package com.ijikod.di.githubapi

import com.ijikod.di.githubapi.model.ContributorApiModel
import com.ijikod.di.githubapi.model.RepoApiModel
import com.ijikod.di.githubapi.model.UserApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

interface GitHubApi {

    @GET("search/repositories?q=language:kotlin&order=desc&sort=stars")
    suspend fun getTopRepositories(): TopRepoSearchResult

    @GET("repos/{owner}/{name}")
    suspend fun getRepo(
            @Path("owner") repoOwner: String,
            @Path("name") repoName: String
    ): RepoApiModel

    @GET("repos/{owner}/{name}/contributors")
    suspend fun getContributors(
            @Path("owner") repoOwner: String,
            @Path("name") repoName:String
    ): List<ContributorApiModel>
}

