package com.ijikod.di.appdeps

import android.content.Context
import com.ijikod.di.repository.AppRepository

interface ApplicationDeps {

    fun appRepository(): AppRepository
}

fun Context.applicationDeps(): ApplicationDeps {
    return (applicationContext as? HasApplicationDeps)?.getApplicationDeps()
        ?: throw IllegalArgumentException("Application must implement hasAppilcationDeps")
}