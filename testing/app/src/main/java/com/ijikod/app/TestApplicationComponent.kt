package com.ijikod.app

import android.content.Context
import com.ijikod.app.githubapi.FakeGitHubApi
import com.ijikod.app.githubapi.TestGitHubApiModule
import com.ijikod.appcomponent.ApplicationComponent
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestGitHubApiModule::class])
interface TestApplicationComponent :ApplicationComponent {

    fun gitHubApi() : FakeGitHubApi

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context) : TestApplicationComponent
    }
}