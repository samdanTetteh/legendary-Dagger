package com.ijikod.app.navigation

import com.ijikod.navigation.ScreenNavigator
import dagger.Binds
import dagger.Module

@Module
interface TestNavigationModule {

    @Binds
    fun bindScreenNavigation (fakeScreenNavigation: FakeScreenNavigator): ScreenNavigator
}