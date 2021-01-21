package com.ijikod.details

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ijikod.app.TestApplication
import com.ijikod.di.githubapi.model.ContributorApiModel
import com.ijikod.di.githubapi.model.RepoApiModel
import com.ijikod.di.githubapi.model.UserApiModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RepoDetailsFragmentTest(){

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
    fun setUp(){
        val gitHubApi = TestApplication.component.gitHubApi()
        gitHubApi.singleRepoResult = FAKE_REPO
        gitHubApi.contributorsResults = FAKE_CONTRIBUTORS
    }


    @Test
    fun loadedStateDisplaysExpectedData() {
        launchFragmentInContainer<RepoDetailsFragment> (
            fragmentArgs = Bundle().apply {
                putString("repo_owner", "owner")
                putString("repo_name", "name")
            }
        )

        onView(withId(R.id.details_loading_indicator)).check(matches(
            withEffectiveVisibility(ViewMatchers.Visibility.GONE)))

        onView(withId(R.id.contributors_loading_indicator)).check(matches(
            withEffectiveVisibility(ViewMatchers.Visibility.GONE)))

        onView(withId(R.id.repo_name)).check(matches(withText(FAKE_REPO.name)))
        onView(withId(R.id.repo_description)).check(matches(withText(FAKE_REPO.description)))

        onView(withId(R.id.contributorName)).check(matches(withText(FAKE_CONTRIBUTORS[0].login)))
    }


}