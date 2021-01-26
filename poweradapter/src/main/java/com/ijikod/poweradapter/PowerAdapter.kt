package com.ijikod.poweradapter

import javax.inject.Provider


class PowerAdapter (
    private val renderers: Map<Class<out RecyclerItem>, Provider<ItemRenderer<out RecyclerItem>>>
) {
}