package com.ijikod.di.application

import android.app.Application
import com.ijikod.di.appdeps.ApplicationDeps
import com.ijikod.di.appdeps.HasApplicationDeps

class GitHubBrowserApp : Application(), HasApplicationDeps{

    private val appComponent by lazy { DaggerApplicationComponent.factory().create(this) }

    override fun getApplicationDeps(): ApplicationDeps {
        return appComponent
    }
}