package com.ijikod.appcomponent

import android.content.Context
import com.ijikod.di.appdeps.ApplicationDeps
import com.ijikod.di.githubapi.GitHubApiModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GitHubApiModule::class])
interface ApplicationComponent : ApplicationDeps {


    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}