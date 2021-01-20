package com.ijikod.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.ijikod.app.githubapi.FakeGitHubApi
import com.ijikod.details.list.ContributorItem
import com.ijikod.di.githubapi.model.ContributorApiModel
import com.ijikod.di.githubapi.model.RepoApiModel
import com.ijikod.di.githubapi.model.UserApiModel
import com.ijikod.di.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepoDetailsViewModelTest(){

    @get:Rule
    val taskExecutableRule = InstantTaskExecutorRule()


    private lateinit var viewModel: RepoDetailsViewModel
    private lateinit var repoInfoViewStateValues: MutableList<RepoInfoViewSate>
    private lateinit var repoContributorsViewStateValues: MutableList<RepoContributorsViewState>

    private val FAKE_REPO =
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


    private val FAKE_CONTRIBUTORS = listOf(ContributorApiModel(id= 1L, login = "contributor", avatarUrl = "avatar.png"))


    @Before
    fun setup(){
        Dispatchers.setMain(Dispatchers.Unconfined)

        repoInfoViewStateValues = mutableListOf()
        repoContributorsViewStateValues = mutableListOf()

        val appRepository = AppRepository(FakeGitHubApi().apply {
            singleRepoResult = FAKE_REPO
            contributorsResults = FAKE_CONTRIBUTORS
        })

        viewModel = RepoDetailsViewModel(repoOwner = "repo_owner", repoName = "repo_name", appRepository)
        viewModel.repoInfoUpdates.observeForever {
            repoInfoViewStateValues.add(it)
        }
        viewModel.contributorsUpdates.observeForever {
            repoContributorsViewStateValues.add(it)
        }
    }


    @Test
    fun `repo info loaded`(){
        Truth.assertThat(repoInfoViewStateValues.size).isEqualTo(1)
        val expectedState = RepoInfoViewStateLoaded (
            repoName = FAKE_REPO.name,
            repoDescription = FAKE_REPO.description ?: "",
            createdDate = FAKE_REPO.createdDate,
            updatedDate = FAKE_REPO.updatedDate
        )

        Truth.assertThat(repoInfoViewStateValues[0]).isEqualTo(expectedState)
    }


    @Test
    fun `repo contributors loaded`() {
        Truth.assertThat(repoContributorsViewStateValues.size).isEqualTo(1)
        val expectedState = RepoContributorsViewStateLoaded (
            contributors = FAKE_CONTRIBUTORS.map { apiModel ->
                ContributorItem(
                    id = apiModel.id,
                    name = apiModel.login,
                    avatarUrl = apiModel.avatarUrl
                )
            }
        )

        Truth.assertThat(repoContributorsViewStateValues[0]).isEqualTo(expectedState)
    }
}