package com.ijikod.di.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ijikod.di.githubapi.GitHubApi
import com.ijikod.di.githubapi.model.RepoApiModel
import com.ijikod.di.githubapi.model.UserApiModel
import com.ijikod.di.repository.AppRepository
import com.google.common.truth.Truth.assertThat
import com.ijikod.app.githubapi.FakeGitHubApi
import com.ijikod.di.home.list.RepoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule


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

class HomeViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var viewStateValues : MutableList<HomeViewState>

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        val appRepository = AppRepository(FakeGitHubApi().apply {
            repos = listOf(FAKE_REPO, FAKE_REPO)
        })
        viewStateValues = mutableListOf()

        viewModel = HomeViewModel(appRepository)
        viewModel.viewStateUpdates.observeForever { viewStateValues.add(it)}
    }

    @Test
    fun `loaded state contains repos models`(){
        assertThat(viewStateValues.size).isEqualTo(1)
        val expectedState = HomeViewSateLoaded(repos = listOf(
            FAKE_REPO.toRepoItem(), FAKE_REPO.toRepoItem()
        ) )

        assertThat(viewStateValues[0]).isEqualTo(expectedState)
    }
}