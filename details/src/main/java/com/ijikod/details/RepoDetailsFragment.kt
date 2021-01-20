package com.ijikod.details

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ijikod.details.databinding.ScreenDetailsBinding
import com.ijikod.details.list.ContributorsAdapter
import com.ijikod.di.di.viewmodel.AppViewModelFactory
import javax.inject.Inject

class RepoDetailsFragment : Fragment() {

    companion object {
        fun newInstance(repoOwner: String, repoName: String): RepoDetailsFragment {
            val args = Bundle().apply {
                putString("repo_name", repoName)
                putString("repo_owner", repoOwner)
            }

            return RepoDetailsFragment().apply { arguments = args }
        }
    }

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RepoDetailsViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ScreenDetailsBinding.inflate(inflater, container, false)

        setUpContributorList(binding)

        observeViewModeStates(binding)
        return binding.root
    }

    private fun observeViewModeStates(binding: ScreenDetailsBinding) {
        viewModel.repoInfoUpdates.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is RepoInfoViewStateLoading -> handleInfoLoadingState(binding)
                is RepoInfoViewStateLoaded -> handleInfoLoadedState(binding, state)
            }
        })

        viewModel.contributorsUpdates.observe (viewLifecycleOwner, Observer { state ->
            when (state) {
                is RepoContributorsViewStateLoading -> handleContributorsLoadingList(binding)
                is RepoContributorsViewStateLoaded -> handleContributorsLoadedList(binding, state)
            }
        })
    }

    private fun handleContributorsLoadedList(binding: ScreenDetailsBinding, state: RepoContributorsViewStateLoaded) {
        binding.contributorsLoadingIndicator.visibility = View.GONE
        binding.contributorList.visibility = View.VISIBLE

        (binding.contributorList.adapter as ContributorsAdapter).setContributors(state.contributors)
    }

    private fun handleContributorsLoadingList(binding: ScreenDetailsBinding) {
        binding.detailsLoadingIndicator.visibility = View.VISIBLE
        binding.contributorList.visibility = View.GONE
    }

    private fun handleInfoLoadedState(binding: ScreenDetailsBinding, state: RepoInfoViewStateLoaded) {
        binding.detailsLoadingIndicator.visibility = View.GONE
        binding.toggleInfoTextVisibility(View.VISIBLE)

        binding.repoName.text = state.repoName
        binding.repoDescription.text = state.repoDescription
        binding.creationDate.text = state.createdDate
        binding.updatedDate.text = state.updatedDate
    }

    private fun handleInfoLoadingState(binding: ScreenDetailsBinding) {
        binding.detailsLoadingIndicator.visibility = View.VISIBLE
        binding.toggleInfoTextVisibility(View.GONE)
    }


    private fun setUpContributorList(binding: ScreenDetailsBinding) {
        binding.contributorList.apply {
            adapter = ContributorsAdapter()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    private fun ScreenDetailsBinding.toggleInfoTextVisibility(visibility: Int) {
        repoName.visibility = visibility
        repoDescription.visibility = visibility
        createdDateLabel.visibility = visibility
        creationDate.visibility = visibility
        updatedDateLabel.visibility = visibility
        updatedDate.visibility = visibility
    }
}