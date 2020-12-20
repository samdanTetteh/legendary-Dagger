package com.ijikod.app.githubapi

import com.ijikod.di.githubapi.GitHubApi
import dagger.Binds
import dagger.Module

@Module
interface TestGitHubApiModule {

    @Binds
    fun bindsGitHubApi(fakeGitHubApi: FakeGitHubApi) : GitHubApi
}