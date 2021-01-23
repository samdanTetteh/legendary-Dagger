package com.ijikod.di.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ijikod.di.di.scope.ScreenScope
import com.ijikod.di.repository.AppRepository
import com.ijikod.navigation.DetailsScreen
import com.ijikod.navigation.ScreenNavigator
import kotlinx.coroutines.launch
import javax.inject.Inject

@ScreenScope
class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val screenNavigator: ScreenNavigator
) : ViewModel() {

    private val _viewState = MutableLiveData<HomeViewState>(HomeViewStateLoading)
    val viewStateUpdates: LiveData<HomeViewState> = _viewState

    init {
        viewModelScope.launch {
            val topRepos = appRepository.getTopGitHubRepos()
            _viewState.value = HomeViewSateLoaded(
                repos = topRepos.map {
                    it.toRepoItem()
                }
            )
        }
    }


    fun onRepoSelected(repoOwner: String, repoName: String){
        screenNavigator.goToScreen(DetailsScreen(repoOwner, repoName))
    }

}