package com.ijikod.poweradapter

import androidx.recyclerview.widget.RecyclerView
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class RendererKey(val value: KClass<out RecyclerItem>)