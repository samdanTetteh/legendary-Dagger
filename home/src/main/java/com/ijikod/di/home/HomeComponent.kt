package com.ijikod.di.home

import com.ijikod.di.appdeps.ApplicationDeps
import com.ijikod.di.appdeps.applicationDeps
import com.ijikod.di.di.component.getComponent
import com.ijikod.di.di.scope.ScreenScope
import dagger.Component

@ScreenScope
@Component(dependencies = [ApplicationDeps::class], modules = [HomeModule::class])
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)

    @Component.Factory
    interface Factory {

        fun create(applicationDeps: ApplicationDeps): HomeComponent
    }
}

fun HomeFragment.inject() {
    getComponent {
        DaggerHomeComponent.factory().create(requireContext().applicationDeps())
    }.inject(this)

}