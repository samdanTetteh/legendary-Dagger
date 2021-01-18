package com.ijikod.details

import androidx.lifecycle.ViewModel
import com.ijikod.di.di.scope.ScreenScope
import com.ijikod.di.repository.AppRepository
import javax.inject.Inject
import javax.inject.Named

@ScreenScope
class RepoDetailsViewModel @Inject constructor(
        @Named("repo_owner") val repoOwner: String,
        @Named("repo_name") val repoName: String,
        private val appRepository: AppRepository
):ViewModel(){


}