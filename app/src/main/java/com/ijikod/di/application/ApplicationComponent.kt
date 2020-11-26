package com.ijikod.di.application

import android.content.Context
import com.ijikod.di.githubapi.GitHubApiModule
import com.ijikod.di.repository.AppRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GitHubApiModule::class])
interface ApplicationComponent {

    fun appRepository(): AppRepository

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}