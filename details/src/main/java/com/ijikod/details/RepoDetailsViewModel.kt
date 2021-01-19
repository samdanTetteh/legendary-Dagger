package com.ijikod.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ijikod.details.list.ContributorItem
import com.ijikod.di.di.scope.ScreenScope
import com.ijikod.di.repository.AppRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@ScreenScope
class RepoDetailsViewModel @Inject constructor(
        @Named("repo_owner") val repoOwner: String,
        @Named("repo_name") val repoName: String,
        private val appRepository: AppRepository
) : ViewModel() {

    private val repoInfoViewSate = MutableLiveData<RepoInfoViewSate>(RepoInfoViewStateLoading)
    val repoInfoUpdates: LiveData<RepoInfoViewSate> = repoInfoViewSate


    private val contributorsViewState =
        MutableLiveData<RepoContributorsViewState>(RepoContributorsViewStateLoading)
    val contributorsUpdates: LiveData<RepoContributorsViewState> = contributorsViewState


    init {
        viewModelScope.launch {
            val repo = appRepository.getRepo(repoOwner, repoName)
            repoInfoViewSate.value = RepoInfoViewStateLoaded(
                    repoName = repo.name,
                    repoDescription = repo.description ?: "",
                    createdDate = repo.createdDate,
                    updatedDate = repo.updatedDate
            )
        }

        viewModelScope.launch {
            val contributors = appRepository.getContributors(repoOwner, repoName)
            contributorsViewState.value = RepoContributorsViewStateLoaded(
                    contributors = contributors.map { apiModel ->
                            ContributorItem(
                                    id = apiModel.id,
                                    name = apiModel.login,
                                    avatarUrl = apiModel.avatarUrl
                            )
                    }
            )
        }
    }
}