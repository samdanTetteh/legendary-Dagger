package com.ijikod.di.home

import androidx.lifecycle.ViewModel
import com.ijikod.di.di.viewmodel.ViewModelKey
import com.ijikod.di.home.list.RepoItem
import com.ijikod.di.home.list.RepoItemRenderer
import com.ijikod.poweradapter.ItemRenderer
import com.ijikod.poweradapter.RecyclerItem
import com.ijikod.poweradapter.RendererKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindViewModel(homeViewModel: HomeViewModel) : ViewModel

    @Binds
    @IntoMap
    @RendererKey(RepoItem::class)
    fun bindRepoItemRenderer(repoItemRenderer: RepoItemRenderer): ItemRenderer<out RecyclerItem>

}