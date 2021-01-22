package com.ijikod.app.navigation

import com.ijikod.navigation.Screen
import com.ijikod.navigation.ScreenNavigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeScreenNavigator @Inject constructor() : ScreenNavigator {


    override fun goToScreen(screen: Screen) {
        TODO("Not yet implemented")
    }


}