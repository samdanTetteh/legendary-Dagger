package com.ijikod.poweradapter

import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.annotation.LayoutRes

interface ItemRenderer<T: RecyclerItem> {

    @LayoutRes
    fun layoutRes(): Int

    fun createView(parent: ViewGroup): View

    fun render(itemView: View, item: T)
}