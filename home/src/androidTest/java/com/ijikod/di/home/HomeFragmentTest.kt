package com.ijikod.di.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ijikod.app.TestApplication
import com.ijikod.di.githubapi.model.RepoApiModel
import com.ijikod.di.githubapi.model.UserApiModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @Before
    fun setUp(){
        val gitHubApi = TestApplication.component.gitHubApi()
        gitHubApi.repos = listOf(
            RepoApiModel(
                id = 1L,
                name = "Home Fragment",
                description = "Developer from Ghana",
                owner = UserApiModel(id= 1L, login = "Dagger"),
                stargazersCount = 1,
                forkCount = 1,
                starCount = 1,
                contributorsUrl = "",
                createdDate = "1/01/2021",
                updatedDate =  "1/02/2021"
            )
        )
    }

    @Test
    fun `repos_displayed`(){
        launchFragmentInContainer<HomeFragment>()
        Espresso.onView(ViewMatchers.withId(R.id.repo_name)).check(ViewAssertions.matches(ViewMatchers.withText("Home Fragment")))
    }
}