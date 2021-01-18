package com.ijikod.details

import androidx.lifecycle.ViewModel
import com.ijikod.di.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RepoDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(RepoDetailsViewModel::class)
    fun bindRepoDetailsViewModel(repoDetailsViewModel: RepoDetailsViewModel):ViewModel
}