package com.ijikod.di

import com.ijikod.di.di.scope.ActivityScope
import com.ijikod.navigation.Screen
import com.ijikod.navigation.ScreenNavigator
import javax.inject.Inject

@ActivityScope
class ActivityDrivenScreenNavigator @Inject constructor(): ScreenNavigator {

    var handleGoToScreen: ((Screen) -> Unit)? = null


    override fun goToScreen(screen: Screen) {
        handleGoToScreen?.invoke(screen)
    }


}