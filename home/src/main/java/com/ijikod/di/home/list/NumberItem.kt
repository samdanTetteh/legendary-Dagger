package com.ijikod.di.home.list

import com.ijikod.poweradapter.RecyclerItem

data class NumberItem(val number: Int) : RecyclerItem {

    override fun getId(): Long {
        return number.toLong()
    }
}