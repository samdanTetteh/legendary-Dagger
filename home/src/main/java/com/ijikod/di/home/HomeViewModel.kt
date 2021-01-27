package com.ijikod.di.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ijikod.di.di.scope.ScreenScope
import com.ijikod.di.home.list.NumberItem
import com.ijikod.di.repository.AppRepository
import com.ijikod.navigation.DetailsScreen
import com.ijikod.navigation.ScreenNavigator
import com.ijikod.poweradapter.RecyclerItem
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
            val listItems = mutableListOf<RecyclerItem>()
            topRepos.forEachIndexed { index, repoApiModel ->
                listItems.add(NumberItem(number = index + 1))
                listItems.add(repoApiModel.toRepoItem())
            }

            _viewState.value = HomeViewSateLoaded(listItems)
        }
    }


    fun onRepoSelected(repoOwner: String, repoName: String) {
        screenNavigator.goToScreen(DetailsScreen(repoOwner, repoName))
    }

}