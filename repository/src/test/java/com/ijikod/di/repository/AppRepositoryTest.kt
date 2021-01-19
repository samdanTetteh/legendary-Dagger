package com.ijikod.di.repository

import com.google.common.truth.Truth.assertThat
import com.ijikod.app.githubapi.FakeGitHubApi
import com.ijikod.di.githubapi.GitHubApi
import com.ijikod.di.githubapi.model.ContributorApiModel
import com.ijikod.di.githubapi.model.RepoApiModel
import com.ijikod.di.githubapi.model.UserApiModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

val FAKE_REPO =
    RepoApiModel(
        id = 1L,
        name = "Samdan",
        description = "Developer from Ghana",
        owner = UserApiModel(id = 1L, login = "Dagger"),
        stargazersCount = 1,
        forkCount = 1,
        contributorsUrl = "",
        createdDate = "1/01/2021",
        updatedDate = "1/02/2021"
    )

class AppRepositoryTest {

    private lateinit var appRepository: AppRepository

    private val fakeGitHubApi = FakeGitHubApi().apply {
        repos = listOf(FAKE_REPO)
    }

    @Before
    fun init() {
        appRepository = AppRepository(fakeGitHubApi)
    }

    @Test
    fun `getTopRepos returns result from GITHubApi`() {
        val topReps = runBlocking { appRepository.getTopGitHubRepos() }

        assertThat(topReps.size).isEqualTo(2)
        assertThat(topReps[0]).isEqualTo(FAKE_REPO)
    }

    @Test
    fun `getTopRepos returns cached result`(){
        val initialRequest = runBlocking { appRepository.getTopGitHubRepos() }

        //Change API return value
        fakeGitHubApi.repos = listOf(FAKE_REPO)

        val secondRequest = runBlocking { appRepository.getTopGitHubRepos() }

        assertThat(initialRequest).isEqualTo(secondRequest)
    }

    @Test
    fun `getRepo returns cached value`(){
        //seed cache
        runBlocking {
            appRepository.getTopGitHubRepos()
        }

        //Set Api to return different model on single repo fetch
        fakeGitHubApi.singleRepoResult = FAKE_REPO.copy(name = "Tobi Josh")

        val singleRepoFetchResult = runBlocking {
            appRepository.getRepo(
                    repoOwner = FAKE_REPO.owner.login,
                    repoName = FAKE_REPO.name
            )
        }

        assertThat(singleRepoFetchResult).isEqualTo(FAKE_REPO)
    }

    @Test
    fun `getRepo returns API value if not in cache`(){
        //seed cache
        runBlocking {
            appRepository.getTopGitHubRepos()
        }

        val expectedModel = FAKE_REPO.copy(name = "Nilandri")
        fakeGitHubApi.singleRepoResult = expectedModel

        val singleRepoFetchResult = runBlocking {
            appRepository.getRepo(
                    repoName = expectedModel.name,
                    repoOwner = expectedModel.owner.login
            )
        }

        assertThat(singleRepoFetchResult).isEqualTo(expectedModel)
    }

    @Test
    fun `getContributors returns API value`(){
        val expectedContributors = listOf(
                ContributorApiModel(
                        id = 1L,
                        login = "contributor",
                        avatarUrl = "avatar.png"
                )
        )

        fakeGitHubApi.contributorsResults = expectedContributors

        val contributors = runBlocking {
            appRepository.getContributors(FAKE_REPO.owner.login, FAKE_REPO.name)
        }

        assertThat(contributors).isEqualTo(expectedContributors)
    }



}