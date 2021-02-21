package com.ijikod.di

import com.ijikod.di.di.component.getComponent
import com.ijikod.di.di.scope.ActivityScope
import com.ijikod.navigation.NavigationDeps
import com.ijikod.navigation.ScreenNavigator
import dagger.Binds
import dagger.Component
import dagger.Module

@ActivityScope
@Component(modules = [MainActivityModule::class])
interface MainActivityComponent : NavigationDeps{

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(): MainActivityComponent
    }
}


@Module
interface MainActivityModule {

    @Binds
    fun bindScreenNavigator (activityDrivenScreenNavigator: ActivityDrivenScreenNavigator) : ScreenNavigator
}

fun MainActivity.injectAndGetComponent(): MainActivityComponent {
    val component = getComponent { DaggerMainActivityComponent.create() }
    component.inject(this)
    return component
}