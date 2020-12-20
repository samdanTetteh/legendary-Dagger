package com.ijikod.di.repository

import com.google.common.truth.Truth.assertThat
import com.ijikod.app.githubapi.FakeGitHubApi
import com.ijikod.di.githubapi.GitHubApi
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
        starCount = 1,
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
    fun `check_successful_query`() {
        val topReps = runBlocking { appRepository.getTopGitHubRepos() }

        assertThat(topReps.size).isEqualTo(2)
        assertThat(topReps[0]).isEqualTo(FAKE_REPO)
    }
}