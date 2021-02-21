package com.ijikod.app

import android.content.Context
import com.ijikod.app.githubapi.FakeGitHubApi
import com.ijikod.app.githubapi.TestGitHubApiModule
import com.ijikod.app.navigation.TestNavigationModule
import com.ijikod.appcomponent.ApplicationComponent
import com.ijikod.navigation.NavigationDeps
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestGitHubApiModule::class, TestNavigationModule::class])
interface TestApplicationComponent :ApplicationComponent, NavigationDeps {

    fun gitHubApi() : FakeGitHubApi

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context) : TestApplicationComponent
    }
}