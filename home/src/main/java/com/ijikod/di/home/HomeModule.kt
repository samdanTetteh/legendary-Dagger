package com.ijikod.di.home

import androidx.lifecycle.ViewModel
import com.ijikod.di.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindViewModel(homeViewModel: HomeViewModel) : ViewModel
}