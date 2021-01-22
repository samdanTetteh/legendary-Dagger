package com.ijikod.di.application

import com.ijikod.di.MainActivity
import com.ijikod.di.di.component.getComponent
import com.ijikod.di.di.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(): MainActivityComponent
    }
}

fun MainActivity.injectAndGetComponent(): MainActivityComponent {
    val component = getComponent { DaggerMainActivityComponent.create() }
    component.inject(this)
    return component
}