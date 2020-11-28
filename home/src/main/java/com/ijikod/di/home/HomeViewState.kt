package com.ijikod.di.home

import com.ijikod.di.home.list.RepoItem

sealed class HomeViewState
object HomeViewStateLoading : HomeViewState()
data class HomeViewSateLoaded(val repos: List<RepoItem>) : HomeViewState()
data class HomeViewStateError(val message: String) : HomeViewState()