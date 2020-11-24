package com.ijikod.di.githubapi

import dagger.Binds
import dagger.Module

@Module
interface GitHubApiModule {


    @Binds
    fun bindGitHubApi(mockGitHubApi: MockGitHubApi) : GitHubApi
}