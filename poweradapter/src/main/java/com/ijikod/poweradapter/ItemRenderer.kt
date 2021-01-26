package com.ijikod.poweradapter

import android.view.View
import android.view.ViewParent
import androidx.annotation.LayoutRes

interface ItemRenderer<T: RecyclerItem> {

    @LayoutRes
    fun layoutRes(): Int

    fun createView(parent: ViewParent): View

    fun render(itemView: View, item: T)
}