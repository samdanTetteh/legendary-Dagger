package com.ijikod.di.home.list

data class RepoItem(
    val ownerName: String,
    val name: String,
    val description: String,
    val startCount: Int,
    val forkCount: Int
)