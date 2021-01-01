package com.ijikod.di.githubapi

import com.ijikod.di.githubapi.model.RepoApiModel
import com.ijikod.di.githubapi.model.UserApiModel
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

interface GitHubApi {

    @GET("search/repositories?q=language:kotlin&order=desc&sort=stars")
    suspend fun getTopRepositories(): TopRepoSearchResult
}

