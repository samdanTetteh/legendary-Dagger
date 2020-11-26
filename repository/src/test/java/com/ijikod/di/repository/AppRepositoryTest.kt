package com.ijikod.di.repository

import com.google.common.truth.Truth.assertThat
import com.ijikod.di.githubapi.GitHubApi
import com.ijikod.di.githubapi.model.RepoApiModel
import com.ijikod.di.githubapi.model.UserApiModel
import org.junit.Before
import org.junit.Test

val FAKE_REPO =
    RepoApiModel(
        id = 1L,
        name = "Samdan",
        description = "Developer from Ghana",
        owner = UserApiModel(id = 1L, login = "Dagger"),
        stargazersCount = 1,
        forksCount = 1,
        contributorsUrl = "",
        createdDate = "1/01/2021",
        updatedDate = "1/02/2021"
    )

class AppRepositoryTest {

    private lateinit var appRepository: AppRepository

    private val fakeGitHubApi = FakeGitHubApi()

    @Before
    fun init() {
        appRepository = AppRepository(fakeGitHubApi)
    }

    @Test
    fun `check_successful_query`(){
        val topReps = appRepository.getTopGitHubRepos()

        assertThat(topReps.size).isEqualTo(2)
        assertThat(topReps[0]).isEqualTo(FAKE_REPO)
    }
}


private class FakeGitHubApi : GitHubApi {
    override fun getTopRepositories(): List<RepoApiModel> {
        return listOf(FAKE_REPO, FAKE_REPO)
    }


}