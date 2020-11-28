package com.ijikod.di.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ijikod.di.repository.AppRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    private val _viewState = MutableLiveData<HomeViewState>(HomeViewStateLoading)
    private val viewStateUpdates: LiveData<HomeViewState> = _viewState

    init {
        val topRepos = appRepository.getTopGitHubRepos()
        _viewState.value = HomeViewSateLoaded(
            repos = topRepos.map {
                it.toRepoItem()
            }
        )
    }
}