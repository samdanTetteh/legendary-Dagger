package com.ijikod.di.home.list

import com.ijikod.poweradapter.RecyclerItem

data class RepoItem(
    val ownerName: String,
    val name: String,
    val description: String,
    val startCount: Int,
    val forkCount: Int
) : RecyclerItem {
    override fun getId(): Long {
        return ("$ownerName.$name".hashCode()).toLong()
    }
}