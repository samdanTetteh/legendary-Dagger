package com.ijikod.di.home

import com.ijikod.poweradapter.RecyclerItem

sealed class HomeViewState
object HomeViewStateLoading : HomeViewState()
data class HomeViewSateLoaded(val repos: List<RecyclerItem>) : HomeViewState()
data class HomeViewStateError(val message: String) : HomeViewState()