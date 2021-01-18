package com.ijikod.details

import com.ijikod.di.appdeps.ApplicationDeps
import com.ijikod.di.appdeps.applicationDeps
import com.ijikod.di.di.component.getComponent
import com.ijikod.di.di.scope.ScreenScope
import dagger.BindsInstance
import dagger.Component
import java.lang.NullPointerException
import javax.inject.Named

@ScreenScope
@Component(dependencies = [ApplicationDeps::class], modules = [RepoDetailsModule::class])
interface RepoDetailsComponent {

    fun inject(repoDetailsFragment: RepoDetailsFragment)


    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance @Named("repo_owner") repoOwner: String,
            @BindsInstance @Named("repo_name") repoName: String,
            applicationDeps: ApplicationDeps
        ): RepoDetailsComponent
    }
}


fun RepoDetailsFragment.inject(){
    getComponent {
        val repoOwner = arguments?.getString("repo_owner")
            ?: throw NullPointerException("repo_owner must be supplied in RepoDetailsFragment args")
        val repoName = arguments?.getString("repo_name")
            ?: throw NullPointerException("repo_name must be supplied in the RepoDetailsFragment args")

        return@getComponent DaggerRepoDetailsComponent.factory().create(
            repoOwner,
            repoName,
            requireContext().applicationDeps()
        )
    }.inject(this)
}